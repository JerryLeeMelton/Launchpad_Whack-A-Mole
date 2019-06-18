package com.jerryleemelton.lpwhackamole;

public class LaunchpadChar {

    private boolean[][] data;
    private char id;

    LaunchpadChar(boolean[][] data, char id) {
        this.data = data;
        this.id = id;
    }

    boolean[][] getData() {
        return data;
    }

    char getId() {
        return id;
    }

}
