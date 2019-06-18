package com.jerryleemelton.lpwhackamole;

import javax.sound.midi.*;
import java.util.Arrays;

public class LaunchpadHandler implements Receiver {

    private Thread monitor;

    private String deviceName;

    private MidiDevice launchpadInputDevice;  // Object for inputting MIDI to the Launchpad (Has Receivers)
    private MidiDevice launchpadOutputDevice;  // Object for outputting MIDI from the Launchpad (Has Transmitters)
    private Receiver midiInputReceiver;  // Variable for LP's "MIDI In Port", so to speak
    private Transmitter midiOutputTransmitter;  // Variable for LP's "MIDI Out Port", so to speak
    private boolean isOpen = false;

    // LED Light codes ================================================================================================
    public static final int LIGHT_TRANSPARENT = -1;
    public static final int LIGHT_OFF = 12;
    public static final int LIGHT_RED_LOW = 13;
    public static final int LIGHT_RED_HIGH = 15;
    public static final int LIGHT_AMBER_LOW = 29;
    public static final int LIGHT_AMBER_HIGH = 63;
    public static final int LIGHT_YELLOW_LOW = 62;
    public static final int LIGHT_GREEN_LOW = 28;
    public static final int LIGHT_GREEN_HIGH = 60;

    // Class Constructors =============================================================================================
    LaunchpadHandler() {
        this("Launchpad Mini");
    }

    LaunchpadHandler(String deviceName) {
        // Set device name
        this.deviceName = deviceName;

        // Get Input MidiDevice using deviceName
        launchpadInputDevice = MidiDeviceHandler.getMidiInputDevice(deviceName);
        System.out.println("launchpadInputDevice max transmitters: " + launchpadInputDevice.getMaxTransmitters());
        System.out.println("launchpadInputDevice max receivers: " + launchpadInputDevice.getMaxReceivers());

        // Get Output MidiDevice using deviceName
        launchpadOutputDevice = MidiDeviceHandler.getMidiOutputDevice(deviceName);
        System.out.println("launchpadOutputDevice max transmitters: " + launchpadOutputDevice.getMaxTransmitters());
        System.out.println("launchpadOutputDevice max receivers: " + launchpadOutputDevice.getMaxReceivers());
    }

    // Class Methods ==================================================================================================

    void openLaunchpad() {
        try {
//            // Open the LP's input device
//            launchpadInputDevice.open();
//            System.out.println(deviceName + "'s input device opened!");
//
//            // Create a Transmitter
//            midiOutputTransmitter = launchpadInputDevice.getTransmitter();
//            System.out.println("MIDI Transmitter for Launchpad created!");
//
//            midiOutputTransmitter.setReceiver(this);
//            System.out.println("Transmitter connected to the " + deviceName + " object's Receiver!");

            // Open the output MidiDevice assigned to LP, register this object's receiver with the device.
            launchpadOutputDevice.open();
            midiInputReceiver = launchpadOutputDevice.getReceiver();

            launchpadInputDevice.open();
            midiOutputTransmitter = launchpadInputDevice.getTransmitter();

            isOpen = true;

            monitor = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(isOpen && isConnected()) {
                            try {
                                Thread.sleep(3);
                            }
                            catch(InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "Launchpad_Monitor_for_" + deviceName);
        }
        catch(MidiUnavailableException e) {
            System.out.println("The Launchpad's input device was unavailable!\n");
            e.printStackTrace();
        }

    }

//    public static int coordToData(int colomn, int row) {
//        int data = (row - 1) * 16 + colomn;
//        if (data < 0)
//            data += 120;
//        return data;
//    }

    void connectToOutput(Receiver r) {
        midiOutputTransmitter.setReceiver(r);
    }

    public Receiver getMidiInputReceiver() {
        return midiInputReceiver;
    }

    int coordinateToPad(int row, int col) {
        if(row == 0) {
            return 104 + col;
        }
        else {
            return ((row - 1) * 16) + col;
        }
    }

    // Sends a
    void sendMidiToLaunchpad(MidiMessage message) {
        midiInputReceiver.send(message, -1);
    }

    void testLEDs() {
        try {
            for (int i = 125; i <= 127; i++) {
                ShortMessage sm = new ShortMessage();
                sm.setMessage(176, 0, 0, i);
                sendMidiToLaunchpad(sm);
                Thread.sleep(500);
            }
        } catch (InvalidMidiDataException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void resetLaunchpad() {
        try {
            sendMidiToLaunchpad(new ShortMessage(176, 0, 0));
        } catch(InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    private boolean isConnected() {
        return Arrays.asList(MidiDeviceHandler.getAvailableDevices()).contains(launchpadInputDevice.getDeviceInfo());
    }

    // Implement Methods ==============================================================================================

    // Receiver Methods
    @Override
    public void send(MidiMessage message, long timeStamp) {

    }

    @Override
    public void close() {
        resetLaunchpad();
    }
}
