package com.jerryleemelton.lpwhackamole;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

import static com.jerryleemelton.lpwhackamole.LaunchpadHandler.*;

public class Mole extends Thread {

    LaunchpadHandler lp;
    WhackAMoleLPBoard board;
    private int speed;
    private int pad;
    private int stepCounter;

    Mole(LaunchpadHandler lp, WhackAMoleLPBoard board, int speed, int pad) {
        this.lp = lp;
        this.board = board;
        this.pad = pad;
        this.speed = speed;
        this.stepCounter = 0;

        board.setOccupied(pad, true);
    }


    @Override
    public void run() {
        while (board.padIsOccupied(pad)) {
            if (stepCounter == 0) {  //Mole's first appearance, light is amber =========================================
                try {
                    stepCounter++;
                    board.setOccupied(pad, true);
                    lp.sendMidiToLaunchpad(new ShortMessage(144, pad, LIGHT_AMBER_HIGH));
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(speed / 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (stepCounter == 1) {  //Mole's time is half over, light is red =====================================
                try {
                    stepCounter++;
                    lp.sendMidiToLaunchpad(new ShortMessage(144, pad, LIGHT_RED_HIGH));
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(speed / 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {  //Player missed the mole, light is OFF =============================================================
                try {
                    lp.sendMidiToLaunchpad(new ShortMessage(144, pad, LIGHT_OFF));
                    board.setOccupied(pad, false);
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
