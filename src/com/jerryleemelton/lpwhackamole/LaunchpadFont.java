package com.jerryleemelton.lpwhackamole;

import java.util.ArrayList;

public class LaunchpadFont {

    LaunchpadChar[] font;

    public final LaunchpadChar A = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true,  true,  true,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true}
    }, 'A');

    public final LaunchpadChar B = new LaunchpadChar(new boolean[][] {
            { true,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true,  true,  true, false}
    }, 'B');

    public final LaunchpadChar C = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, 'C');

    public final LaunchpadChar D = new LaunchpadChar(new boolean[][] {
            { true,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true,  true,  true, false}
    }, 'D');

    public final LaunchpadChar E = new LaunchpadChar(new boolean[][] {
            { true,  true,  true,  true},
            { true, false, false, false},
            { true, false, false, false},
            { true,  true,  true, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false},
            { true,  true,  true,  true}
    }, 'E');

    public final LaunchpadChar F = new LaunchpadChar(new boolean[][] {
            { true,  true,  true,  true},
            { true, false, false, false},
            { true, false, false, false},
            { true,  true,  true, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false}
    }, 'F');

    public final LaunchpadChar G = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false, false},
            { true, false,  true,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true,  true}
    }, 'G');

    public final LaunchpadChar H = new LaunchpadChar(new boolean[][] {
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true,  true,  true,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true}
    }, 'H');

    public final LaunchpadChar I = new LaunchpadChar(new boolean[][] {
            { true,  true,  true},
            {false,  true, false},
            {false,  true, false},
            {false,  true, false},
            {false,  true, false},
            {false,  true, false},
            {false,  true, false},
            { true,  true,  true}
    }, 'I');

    public final LaunchpadChar J = new LaunchpadChar(new boolean[][] {
            {false, false, false,  true},
            {false, false, false,  true},
            {false, false, false,  true},
            {false, false, false,  true},
            {false, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, 'J');

    public final LaunchpadChar K = new LaunchpadChar(new boolean[][] {
            { true, false, false,  true},
            { true, false,  true, false},
            { true, false,  true, false},
            { true,  true, false, false},
            { true,  true, false, false},
            { true, false,  true, false},
            { true, false,  true, false},
            { true, false, false,  true}
    }, 'K');

    public final LaunchpadChar L = new LaunchpadChar(new boolean[][] {
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false},
            { true,  true,  true,  true}
    }, 'L');

    public final LaunchpadChar M = new LaunchpadChar(new boolean[][] {
            { true, false, false, false, true},
            { true,  true, false,  true, true},
            { true, false,  true, false, true},
            { true, false,  true, false, true},
            { true, false,  true, false, true},
            { true, false,  true, false, true},
            { true, false,  true, false, true},
            { true, false,  true, false, true}
    }, 'M');

    public final LaunchpadChar N = new LaunchpadChar(new boolean[][] {
            { true, false, false,  true},
            { true,  true, false,  true},
            { true,  true, false,  true},
            { true,  true, false,  true},
            { true, false,  true,  true},
            { true, false,  true,  true},
            { true, false,  true,  true},
            { true, false, false,  true}
    }, 'N');

    public final LaunchpadChar O = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, 'O');

    public final LaunchpadChar P = new LaunchpadChar(new boolean[][] {
            { true,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true,  true,  true, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false},
            { true, false, false, false}
    }, 'P');

    public final LaunchpadChar Q = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false, false},
            { true, false, false,  true, false},
            { true, false, false,  true, false},
            { true, false, false,  true, false},
            { true, false, false,  true, false},
            { true, false,  true, false, false},
            { true, false, false,  true, false},
            {false,  true,  true, false,  true}
    }, 'Q');

    public final LaunchpadChar R = new LaunchpadChar(new boolean[][] {
            { true,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true,  true,  true, false},
            { true, false,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true}
    }, 'R');

    public final LaunchpadChar S = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false, false},
            {false,  true,  true, false},
            {false, false, false,  true},
            {false, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, 'S');

    public final LaunchpadChar T = new LaunchpadChar(new boolean[][] {
            { true,  true,  true,  true,  true},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false}
    }, 'T');

    public final LaunchpadChar U = new LaunchpadChar(new boolean[][] {
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, 'U');

    public final LaunchpadChar V = new LaunchpadChar(new boolean[][] {
            { true, false, false, false,  true},
            { true, false, false, false,  true},
            { true, false, false, false,  true},
            { true, false, false, false,  true},
            {false,  true, false,  true, false},
            {false,  true, false,  true, false},
            {false,  true, false,  true, false},
            {false, false,  true, false, false}
    }, 'V');

    public final LaunchpadChar W = new LaunchpadChar(new boolean[][] {
            { true, false,  true, false,  true},
            { true, false,  true, false,  true},
            { true, false,  true, false,  true},
            { true, false,  true, false,  true},
            { true, false,  true, false,  true},
            { true, false,  true, false,  true},
            {false,  true, false,  true, false},
            {false,  true, false,  true, false}
    }, 'W');

    public final LaunchpadChar X = new LaunchpadChar(new boolean[][] {
            { true, false, false, false,  true},
            { true, false, false, false,  true},
            {false,  true, false,  true, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false,  true, false,  true, false},
            { true, false, false, false,  true},
            { true, false, false, false,  true}
    }, 'X');

    public final LaunchpadChar Y = new LaunchpadChar(new boolean[][] {
            { true, false, false, false,  true},
            { true, false, false, false,  true},
            {false,  true, false,  true, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false},
            {false, false,  true, false, false}
    }, 'Y');

    public final LaunchpadChar Z = new LaunchpadChar(new boolean[][] {
            { true,  true,  true,  true},
            {false, false, false,  true},
            {false, false,  true, false},
            {false, false,  true, false},
            {false,  true, false, false},
            {false,  true, false, false},
            { true, false, false, false},
            { true,  true,  true,  true}
    }, 'Z');

    // NUMBERS =========================================================================================================
    public final LaunchpadChar ZERO = new LaunchpadChar(new boolean[][] {
            {false,  true,  true,  true, false},
            { true, false, false, false,  true},
            { true, false, false, false,  true},
            { true, false,  true, false,  true},
            { true, false,  true, false,  true},
            { true, false, false, false,  true},
            { true, false, false, false,  true},
            {false,  true,  true,  true, false}
    }, '0');

    public final LaunchpadChar ONE = new LaunchpadChar(new boolean[][] {
            {false,  true},
            { true,  true},
            {false,  true},
            {false,  true},
            {false,  true},
            {false,  true},
            {false,  true},
            {false,  true}
    }, '1');

    public final LaunchpadChar TWO = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            {false, false, false,  true},
            {false, false,  true, false},
            {false,  true, false, false},
            { true, false, false, false},
            { true,  true,  true,  true}
    }, '2');

    public final LaunchpadChar THREE = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            {false, false, false,  true},
            {false,  true,  true, false},
            {false, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, '3');

    public final LaunchpadChar FOUR = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false,  true, false},
            { true, false,  true, false},
            { true, false,  true, false},
            { true,  true,  true,  true},
            {false, false,  true, false},
            {false, false,  true, false},
            {false, false,  true, false}
    }, '4');

    public final LaunchpadChar FIVE = new LaunchpadChar(new boolean[][] {
            { true,  true,  true,  true},
            { true, false, false, false},
            { true, false, false, false},
            { true,  true,  true, false},
            {false, false, false,  true},
            {false, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, '5');

    public final LaunchpadChar SIX = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false, false},
            { true,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, '6');

    public final LaunchpadChar SEVEN = new LaunchpadChar(new boolean[][] {
            { true,  true,  true,  true},
            {false, false, false,  true},
            {false, false, false,  true},
            {false, false,  true, false},
            {false, false,  true, false},
            {false, false,  true, false},
            {false,  true, false, false},
            {false,  true, false, false}
    }, '7');

    public final LaunchpadChar EIGHT = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, '8');

    public final LaunchpadChar NINE = new LaunchpadChar(new boolean[][] {
            {false,  true,  true, false},
            { true, false, false,  true},
            { true, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true,  true},
            {false, false, false,  true},
            { true, false, false,  true},
            {false,  true,  true, false}
    }, '9');

    // OTHER CHARACTERS ================================================================================================
    public final LaunchpadChar SPACE = new LaunchpadChar(new boolean[][] {
            {false, false},
            {false, false},
            {false, false},
            {false, false},
            {false, false},
            {false, false},
            {false, false}
    }, ' ');

    public final LaunchpadChar EXLAMATION = new LaunchpadChar(new boolean[][] {
            { true},
            { true},
            { true},
            { true},
            { true},
            { true},
            {false},
            { true}
    }, '!');

    public LaunchpadFont() {
        font = new LaunchpadChar[]{A, B, C, D, E, F, G, H, I, J , K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
                                   ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SPACE};
    }

    // TODO: Implement this method
    public boolean[][] stringToLPBoard(String input) {
        ArrayList<boolean[]> output = new ArrayList<>();
        boolean[] currentColumn = new boolean[8];
        boolean[][] currentChar;

        // Add 8 blank (false) columns to the front of the ArrayList
        for(int col = 0; col < 8; col++) {
            output.add(currentColumn);
        }

        // For each character in the input string
        for (char c: input.toCharArray()) {
            currentChar = charToLPBoard(c);

            // Add each column of the character to the output ArrayList
            for(int col = 0; col < currentChar[0].length; col++) {
                currentColumn = new boolean[8];
                for(int row = 0; row < currentChar.length; row++) {
                        currentColumn[row] = currentChar[row][col];
                }
                output.add(currentColumn);
            }
            output.add(new boolean[8]);
        }

        currentColumn = new boolean[8];
        // Add 8 blank (false) columns to the end of the ArrayList
        for(int col = 0; col < 8; col++) {
            output.add(currentColumn);
        }

        boolean[][] outputArray = new boolean[output.size()][];

        for (int i = 0; i < output.size(); i++) {
            outputArray[i] = output.get(i);
        }

        return outputArray;
    }

    public boolean[][] charToLPBoard(char c) {
        for (LaunchpadChar character : font) {
            if(character.getId() == c) {
                return character.getData();
            }
        }

        return SPACE.getData();
    }
}
