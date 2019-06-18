package com.jerryleemelton.lpwhackamole;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

public class LaunchpadFX {

    LaunchpadHandler lp;
    LaunchpadFont font;
    int[] lpColorsHigh;

    // Variables for the style of clearing the Launchpad board
    public static final int CLEARSTYLE_ALL_AT_ONCE = 0;

    public static final int CLEARSTYLE_ROW_FROM_TOP_LEFT = 1;
    public static final int CLEARSTYLE_ROW_FROM_TOP_RIGHT = 2;

    public static final int CLEARSTYLE_ROW_FROM_BOTTOM_LEFT = 3;
    public static final int CLEARSTYLE_ROW_FROM_BOTTOM_RIGHT = 4;

    public static final int CLEARSTYLE_FROM_CENTER_CLOCKWISE = 5;
    public static final int CLEARSTYLE_FROM_CENTER_COUNTERCLOCKWISE = 6;



    public LaunchpadFX(LaunchpadHandler launchpadHandler) {
        this.lp = launchpadHandler;
        this.font = new LaunchpadFont();
        lpColorsHigh = new int[] {lp.LIGHT_GREEN_HIGH, lp.LIGHT_AMBER_HIGH, lp.LIGHT_RED_HIGH};
    }


    public void flashBoard(int numberOfCycles, int flashSpeedInMs, int clearStyle) {
        // Send MIDI message to activate buffered mode on Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        // Boolean for switching back and forth between Launchpad's buffer one and buffer two
        boolean bufferZero = true;

        // Update buffer with new color values and then display the buffer to the Launchpad
        while (numberOfCycles > 0) {
            // Setup int for array index (modulo 3 ensures it's never out of array bounds)
            int colorIndex = numberOfCycles % 3;

            // Iterate over the board and update the pad colors
            for (int row = 1; row < 9; row++){
                for (int col = 0; col < 8; col++) {
                    try {
                        lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), lpColorsHigh[colorIndex]));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }
                    colorIndex++;
                    if (colorIndex == 3) colorIndex = 0;
                }
            }

            // Send message to Launchpad to swap the update and display buffers
            // (displays the buffer we've been updating)
            try {
                if (bufferZero)
                    lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                else
                    lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }

            bufferZero = !bufferZero;

            numberOfCycles--;

            // Delay for flashing of pads
            try {
                Thread.sleep(flashSpeedInMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Send the message to disable buffer mode on the Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 48));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        clearBoard(clearStyle);
    }


    public void clearBoard(int clearStyle) {
        int delay = 20;

        // Send MIDI message to activate buffered mode on Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        boolean bufferZero = true;

        if (clearStyle == CLEARSTYLE_ALL_AT_ONCE) {  // Clears all pads simultaneously =================================
            // Clear all pads in the buffer
            for (int row = 1; row < 9; row++){
                for (int col = 0; col < 8; col++) {
                    try {
                        lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), 0));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Display the cleared pads
            try {
                    lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }
        }
        else if(clearStyle == CLEARSTYLE_ROW_FROM_TOP_LEFT) { // Clear pads, left to right, top to bottom ==============
            for (int row = 1; row < 9; row++){
                for (int col = 0; col < 8; col++) {
                    try {
                        // Clear pad
                        lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), 0));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    // Send message to Launchpad to swap the update and display buffers
                    // (displays the buffer we've been updating)
                    try {
                        if (bufferZero)
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                        else
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    bufferZero = !bufferZero;

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(clearStyle == CLEARSTYLE_ROW_FROM_TOP_RIGHT) { // Clear pads, right to left, top to bottom =============
            for (int row = 1; row < 9; row++){
                for (int col = 7; col >= 0; col--) {
                    try {
                        // Clear pad
                        lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), 0));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    // Send message to Launchpad to swap the update and display buffers
                    // (displays the buffer we've been updating)
                    try {
                        if (bufferZero)
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                        else
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    bufferZero = !bufferZero;

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(clearStyle == CLEARSTYLE_ROW_FROM_BOTTOM_LEFT) { // Clear pads, left to right, bottom to top ===========
            for (int row = 8; row > 0; row--){
                for (int col = 0; col < 8; col++) {
                    try {
                        // Clear pad
                        lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), 0));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    // Send message to Launchpad to swap the update and display buffers
                    // (displays the buffer we've been updating)
                    try {
                        if (bufferZero)
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                        else
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    bufferZero = !bufferZero;

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(clearStyle == CLEARSTYLE_ROW_FROM_BOTTOM_RIGHT) { // Clear pads, right to left, bottom to top ==========
            for (int row = 8; row > 0; row--){
                for (int col = 7; col >= 0; col--) {
                    try {
                        // Clear pad
                        lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), 0));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    // Send message to Launchpad to swap the update and display buffers
                    // (displays the buffer we've been updating)
                    try {
                        if (bufferZero)
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                        else
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    bufferZero = !bufferZero;

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(clearStyle == CLEARSTYLE_FROM_CENTER_CLOCKWISE) {
            int row = 4;
            int col = 3;
            int padMoveIncrement = 1;
            int padTurnCounter = 0;
            int padMoveCounter = 0;

            boolean movingUp = false;
            boolean movingRight = true;
            boolean movingVertical = false;

            for(int i = 0; i < 64; i++) {
                try {
                    // Clear pad
                    lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), 0));
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }

                // Send message to Launchpad to swap the update and display buffers
                // (displays the buffer we've been updating)
                try {
                    if (bufferZero)
                        lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                    else
                        lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }

                bufferZero = !bufferZero;

                if(movingVertical) {
                    if(movingUp) {
                        row--;
                    } else {
                        row++;
                    }
                }else {
                    if(movingRight){
                        col++;
                    }
                    else{
                        col--;
                    }
                }

                padMoveCounter++;

                if(padMoveCounter == padMoveIncrement) {

                    padTurnCounter++;
                    padMoveCounter = 0;

                    if(padTurnCounter == 2) {
                        padMoveIncrement++;
                        padTurnCounter = 0;
                    }

                    if(movingVertical)
                        movingUp = !movingUp;
                    else
                        movingRight = !movingRight;

                    movingVertical = !movingVertical;
                }


                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(clearStyle == CLEARSTYLE_FROM_CENTER_COUNTERCLOCKWISE) {
            int row = 5;
            int col = 4;
            int padMoveIncrement = 1;
            int padTurnCounter = 0;
            int padMoveCounter = 0;

            boolean movingUp = true;
            boolean movingRight = false;
            boolean movingVertical = true;

            for(int i = 0; i < 64; i++) {
                try {
                    // Clear pad
                    lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), 0));
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }

                // Send message to Launchpad to swap the update and display buffers
                // (displays the buffer we've been updating)
                try {
                    if (bufferZero)
                        lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                    else
                        lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }

                bufferZero = !bufferZero;

                if(movingVertical) {
                    if(movingUp) {
                        row--;
                    } else {
                        row++;
                    }
                }else {
                    if(movingRight){
                        col++;
                    }
                    else{
                        col--;
                    }
                }

                padMoveCounter++;

                if(padMoveCounter == padMoveIncrement) {

                    padTurnCounter++;
                    padMoveCounter = 0;

                    if(padTurnCounter == 2) {
                        padMoveIncrement++;
                        padTurnCounter = 0;
                    }

                    if(movingVertical)
                        movingUp = !movingUp;
                    else
                        movingRight = !movingRight;

                    movingVertical = !movingVertical;
                }


                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        // Send the message to disable buffer mode on the Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 48));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }


    void lightNumOfPads(int numPads, int color, int delay) {
        // Send MIDI message to activate buffered mode on Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        // Boolean for switching back and forth between Launchpad's buffer one and buffer two
        boolean bufferZero = true;

        for (int row = 8; row > 0; row--){
            for (int col = 0; col < 8; col++) {
                if(numPads > 0) {
                    try {
                        // Clear pad
                        lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), color));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    // Send message to Launchpad to swap the update and display buffers
                    // (displays the buffer we've been updating)
                    try {
                        if (bufferZero)
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                        else
                            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }

                    bufferZero = !bufferZero;
                    numPads--;

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Send the message to disable buffer mode on the Launchpad
                    try {
                        lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 48));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }

        // Send the message to disable buffer mode on the Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 48));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    void displayScrollingText(String text, int speed, int color) {
        // Send MIDI message to activate buffered mode on Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        // Boolean for switching back and forth between Launchpad's buffer one and buffer two
        boolean bufferZero = true;

        boolean[][] textToDisplay = font.stringToLPBoard(text);

        // Loop over the textToDisplay array until we reach 8 "pixels" from the end
        for(int textColOverall = 0; textColOverall <= textToDisplay.length - 8; textColOverall++) {
            // Loop over the Launchpad board and populate it with the current pad colors
            for(int textColCurrent = 0; textColCurrent <8; textColCurrent++) {
                for(int row = 0; row < 8; row++) {
                    if(textToDisplay[textColCurrent + textColOverall][row]){
                        try {
                            lp.sendMidiToLaunchpad(
                                    new ShortMessage(144, lp.coordinateToPad(row + 1, textColCurrent),
                                            color));
                        } catch (InvalidMidiDataException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            lp.sendMidiToLaunchpad(
                                    new ShortMessage(144, lp.coordinateToPad(row + 1, textColCurrent),
                                            LaunchpadHandler.LIGHT_OFF));
                        } catch (InvalidMidiDataException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            // Send message to Launchpad to swap the update and display buffers
            // (displays the buffer we've been updating)
            try {
                if (bufferZero)
                    lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
                else
                    lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }

            bufferZero = !bufferZero;

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }









        // Send the message to disable buffer mode on the Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 48));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }


    void displayChar(char c, int color) {
        // Send MIDI message to activate buffered mode on Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 49));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
        boolean[][] character = font.charToLPBoard(c);

        // LaunchpadChars are only 7 "pixels" high, so clear the first row.
        for(int col = 0; col < 8; col++) {
            try {
                // Clear pad
                lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(1, col), LaunchpadHandler.LIGHT_OFF));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            }
        }

        for(int row = 2; row < 9; row++) {
            for(int col = 0; col < character[0].length; col++) {
                if(character[row - 2][col]) {
                    try {
                        // Send Color to Pad
                        lp.sendMidiToLaunchpad(new ShortMessage(144, lp.coordinateToPad(row, col), color));
                    } catch (InvalidMidiDataException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Send message to Launchpad to swap the update and display buffers
        // (displays the buffer we've been updating)
        try {
                lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 52));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        // Send the message to disable buffer mode on the Launchpad
        try {
            lp.sendMidiToLaunchpad(new ShortMessage(176, 0, 48));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

}
