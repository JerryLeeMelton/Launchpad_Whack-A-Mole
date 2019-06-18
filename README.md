#Java Launchpad Whack-A-Mole Game

This is a simple Whack-A-Mole game that's played with a Novation Launchpad.  

##Hardware

So far this has only been tested with a Launchpad Mini.

##How to Play

Upon loading up the software and successfully opening the Launchpad, the top row of buttons (the round ones) will display some UI elements before the game starts.

* Button 1 (green) will set the difficulty to easy (slowest game speed, 32 possible points)
* Button 2 (yellow) will set the difficulty to medium (medium game speed, 48 possible points)
* Button 3 (red) will set the difficulty to hard (fastest game speed, 64 possible points)
* Button 8 (green) will start the game
    - If no difficulty is specified, the game will default to easy mode

###Score

At the end of the game the Launchpad will light up with one green light for every "mole" that was successfully hit.  The maximum score is 32 for easy mode, 48 for medium mode, and 64 for hard mode.