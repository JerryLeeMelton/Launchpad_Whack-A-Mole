package com.jerryleemelton.lpwhackamole;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.awt.*;
import java.io.Closeable;
import java.util.Random;

public class WhackAMole implements Runnable, Receiver {

    LaunchpadHandler launchpadHandler;
    WhackAMoleLPBoard launchpadBoard;
    LaunchpadFX lpfx;
    Random rng;
    int score;
    int difficulty;
    int moleSpeed;
    int nextMoleSpeedMult;
    int numMolesCurrent;
    int numMolesTotal;

    int timeToNextMole;
    Point nextMoleCoordinates;

    int DIFFICULTY_EASY = 1;
    int DIFFICULTY_MEDIUM = 2;
    int DIFFICULTY_HARD = 3;

    boolean playing;
    boolean exit;


    public WhackAMole(String launchpadName) {
        this.launchpadHandler = new LaunchpadHandler(launchpadName);
        this.launchpadBoard = new WhackAMoleLPBoard();
        this.lpfx = new LaunchpadFX(launchpadHandler);
        this.rng = new Random();
        nextMoleCoordinates = new Point();
        this.difficulty = 0;
        this.playing = false;
        this.exit = false;

    }

    @Override
    public void run() {

        // Open the Launchpad and reset the pad surface
        launchpadHandler.openLaunchpad();
        launchpadHandler.resetLaunchpad();

        launchpadHandler.connectToOutput(this);

        // Initialize UI Lights on the Launchpad
        try {
            launchpadHandler.sendMidiToLaunchpad(new ShortMessage(
                    176, 104, LaunchpadHandler.LIGHT_GREEN_HIGH)); // Difficulty = EASY

            launchpadHandler.sendMidiToLaunchpad(new ShortMessage(
                    176, 105, LaunchpadHandler.LIGHT_AMBER_HIGH)); // Difficulty = MEDIUM

            launchpadHandler.sendMidiToLaunchpad(new ShortMessage(
                    176, 106, LaunchpadHandler.LIGHT_RED_HIGH)); // Difficulty = HARD

            launchpadHandler.sendMidiToLaunchpad(new ShortMessage(
                    176, 111, LaunchpadHandler.LIGHT_GREEN_HIGH)); // GAME START

        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }


        // Initialize game variables
        score = 0;
        nextMoleSpeedMult = 5;

        rng = new Random();

        //Choose difficulty
        System.out.println("Choose Difficulty and press start to play!");
        while (!playing) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        switch (difficulty) {
            case 1:
                moleSpeed = 1800;
                numMolesCurrent = 32;
                break;
            case 2:
                moleSpeed = 1550;
                numMolesCurrent = 48;
                break;
            case 3:
                moleSpeed = 1350;
                numMolesCurrent = 64;
                break;
            default:
                moleSpeed = 2500;
                numMolesCurrent = 16;
                System.out.println("Invalid Difficulty.  Difficulty Set to easy by Default.");
                break;
        }

        // Play countdown intro
        lpfx.displayScrollingText("3   2   1", 50, launchpadHandler.LIGHT_RED_HIGH);

        while(numMolesCurrent > 0) {

            // Increase speed as game progresses
            if(numMolesCurrent < numMolesTotal / 2) {
                moleSpeed -= 250;
            } else if (numMolesCurrent < numMolesTotal / 3) {
                moleSpeed -= 250;
            }else if (numMolesCurrent < numMolesTotal / 4) {
                moleSpeed -= 250;
            }else if (numMolesCurrent < numMolesTotal / 5) {
                moleSpeed -= 250;
            }

            // Get random values for timeToNextMole and pad coordinates. Check if pad is already occupied.
            // If it is occupied, then regenerate the pad coordinates.
            timeToNextMole = (rng.nextInt(nextMoleSpeedMult) + 2) * (moleSpeed / 15);
            do {
                nextMoleCoordinates.y = rng.nextInt(8) + 1;  // Row
                nextMoleCoordinates.x = rng.nextInt(8);      // Column
            } while(launchpadBoard.padIsOccupied(nextMoleCoordinates.y, nextMoleCoordinates.x));

            // Create a new mole that
            new Mole(this.launchpadHandler, launchpadBoard, moleSpeed,
                    launchpadBoard.coordToPad(nextMoleCoordinates.y, nextMoleCoordinates.x)).start();

            numMolesCurrent--;

            // Put the thread to sleep
            try {
                if(numMolesCurrent <= 0)
                    Thread.sleep(timeToNextMole * 3);
                else
                    Thread.sleep(timeToNextMole);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("GAME OVER!\n" + "Score: " + score);

        difficulty = 0;
        playing = false;

        // Play End-of-game animation/board clear
        lpfx.flashBoard(10, 10, lpfx.CLEARSTYLE_FROM_CENTER_CLOCKWISE);

        lpfx.lightNumOfPads(score, launchpadHandler.LIGHT_GREEN_HIGH, 50);

        try {
            launchpadHandler.sendMidiToLaunchpad(new ShortMessage
                    (176, 110, launchpadHandler.LIGHT_RED_HIGH));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        exit = false;

        while (!exit) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        launchpadHandler.resetLaunchpad();
    }


    @Override
    public void send(MidiMessage message, long timeStamp) {

        if(message instanceof ShortMessage) {

            int messageStatus = message.getStatus();
            int messagePadID = ((ShortMessage) message).getData1();
            int messageData = ((ShortMessage) message).getData2();

            if(messageStatus == 144) {  // One of the square buttons or right side round buttons pressed

                if(messageData > 0 && playing) {  // Game buttons only react when the button is pressed down
                    if(launchpadBoard.padIsOccupied(messagePadID)) {
                        launchpadBoard.setOccupied(messagePadID, false);
                        score++;
                        try {
                            launchpadHandler.sendMidiToLaunchpad(new ShortMessage
                                    (144, messagePadID, launchpadHandler.LIGHT_GREEN_HIGH));
                        } catch (InvalidMidiDataException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (messageData == 0) {
                    try {
                        launchpadHandler.sendMidiToLaunchpad(new ShortMessage
                                (144, messagePadID, 0));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (messageStatus == 176) {  // One of the top round buttons was pressed
                if(messageData == 0) {  // Only react when button is released

                    if (messagePadID == 110) {
                        exit = true;
                    }

                    if(!playing) { // UI Buttons only function when the game isn't playing
                        if(messagePadID == 104) {
                            System.out.println("104 pressed");
                            difficulty = 1;  // EASY
                        } else if (messagePadID == 105) {
                            System.out.println("105 pressed");
                            difficulty = 2; // MEDIUM
                        } else if (messagePadID == 106) {
                            System.out.println("106 pressed");
                            difficulty = 3; // HARD
                        } else if (messagePadID == 111) {
                            System.out.println("111 pressed");
                            playing = true;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void close() {

    }

    public static void main(String[] args) {
        new WhackAMole("Launchpad Mini").run();
    }


}
