package com.jerryleemelton.lpwhackamole;

public class LaunchpadBoard {

    LaunchpadHandler launchpad;

    int[][] padIDs;

    LaunchpadBoard() {
        this.launchpad = launchpad;

        padIDs = new int[][] {
                {104, 105, 106, 107, 108, 109, 110, 111, 127},
                {  0,   1,   2,   3,   4,   5,   6,   7,   8},
                { 16,  17,  18,  19,  20,  21,  22,  23,  24},
                { 32,  33,  34,  35,  36,  37,  38,  39,  40},
                { 48,  49,  50,  51,  52,  53,  54,  55,  56},
                { 64,  65,  66,  67,  68,  69,  70,  71,  72},
                { 80,  81,  82,  83,  84,  85,  86,  87,  88},
                { 96,  97,  98,  99, 100, 101, 102, 103, 104},
                {112, 113, 114, 115, 116, 117, 118, 119, 120}
        };
    }

    public int coordToPad(int row, int col) {
        return padIDs[row][col];
    }




}
