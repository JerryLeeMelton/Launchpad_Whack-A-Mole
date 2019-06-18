package com.jerryleemelton.lpwhackamole;

public class WhackAMoleLPBoard extends LaunchpadBoard {

    private boolean[][] occupied;

    // CONSTRUCTOR
    public WhackAMoleLPBoard (){
        super();
        occupied = new boolean[9][9];
    }

    // GETTERS & SETTERS
    public void setOccupied(int row, int col, boolean isOccupied) {
        occupied[row][col] = isOccupied;
    }

    public void setOccupied(int padID, boolean isOccupied) {
        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                if(padIDs[row][col] == padID) {
                    occupied[row][col] = isOccupied;
                    return;
                }
            }
        }
    }

    public boolean padIsOccupied(int row, int col) {
        return occupied[row][col];
    }

    public boolean padIsOccupied(int padID) {
        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                if (padIDs[row][col] == padID) return occupied[row][col];
            }
        }
        return false;
    }

}
