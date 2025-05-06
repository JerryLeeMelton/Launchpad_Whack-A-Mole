# Launchpad Whack-A-Mole

A "Whack-A-Mole" style game that's played on a Novation Launchpad. This project demonstrates hardware integration by utilizing the Launchpad's grid for interactive gameplay.

## 🎮 Gameplay Overview

- **Objective**: Hit the "moles" as they appear on the Launchpad grid.
- **Controls**:
  - **Difficulty Selection**:
    - Button 1 (Green): Easy (Slowest speed, 32 possible points)
    - Button 2 (Yellow): Medium (Medium speed, 48 possible points)
    - Button 3 (Red): Hard (Fastest speed, 64 possible points)
  - **Start Game**:
    - Button 8 (Green): Begins the game with the selected difficulty.
    - *Note*: If no difficulty is selected, the game defaults to Easy mode.

## 🛠️ Installation & Setup

1. **Prerequisites**:
   - Java Development Kit (JDK) installed (Java 8 or higher)
   - Novation Launchpad (currently tested with Launchpad Mini Mk. II)
   - MIDI drivers and permissions configured on your system

2. **Clone the Repository**:
   ```bash
   git clone https://github.com/JerryLeeMelton/Launchpad_Whack-A-Mole.git
   cd Launchpad_Whack-A-Mole
