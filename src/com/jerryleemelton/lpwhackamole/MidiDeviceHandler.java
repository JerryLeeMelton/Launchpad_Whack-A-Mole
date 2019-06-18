package com.jerryleemelton.lpwhackamole;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;

class MidiDeviceHandler {

    // Arrays to hold available device lists
    private static MidiDevice.Info[] availableDevices;

    private static MidiDevice.Info[] availableInputs;
    private static MidiDevice.Info[] availableOutputs;

    // Gets the MidiDevice.Info objects for
    // all available MIDI devices
    static void listAvailableMidiDevices() {
        MidiDeviceHandler.availableDevices = MidiSystem.getMidiDeviceInfo();

        availableInputs = ListMidiInputDevices(availableDevices);
        availableOutputs = ListMidiOutputDevices(availableDevices);
    }

    // Gets List of all MIDI Input Devices from the list of all MIDI devices.
    static MidiDevice.Info[] ListMidiInputDevices(MidiDevice.Info[] allDevices) {

        // References for:
        // Current working device
        // Working list of verified input devices
        // Array of MidiDevice.Info objects to be returned
        MidiDevice device;
        ArrayList<MidiDevice.Info> deviceList = new ArrayList<>();
        MidiDevice.Info[] devices;

        // Check the list of all devices for input devices
        // (i.e., those devices that have Transmitters)
        for (MidiDevice.Info info : availableDevices) {
            try {
                device = MidiSystem.getMidiDevice(info);

                // Check to see if the device in an input device.
                // If it is, add it to the list.
                if(device.getMaxTransmitters() != 0) deviceList.add(info);
            }
            catch(MidiUnavailableException e) {
                System.out.println("Input device is unavailable.  Not added to list\n");
                e.printStackTrace();
            }

        }

        // Return the array of MIDI Input Devices
        return deviceList.toArray(new MidiDevice.Info[0]);
    }

    // Gets List of all MIDI Input Devices from the list of all MIDI devices.
    static MidiDevice.Info[] ListMidiOutputDevices(MidiDevice.Info[] allDevices) {

        // References for:
        // Current working device
        // Working list of verified output devices
        // Array of MidiDevice.Info objects to be returned
        MidiDevice device;
        ArrayList<MidiDevice.Info> deviceList = new ArrayList<>();
        MidiDevice.Info[] devices;

        // Check the list of all devices for output devices
        // (i.e., those devices that have Receivers)
        for (MidiDevice.Info info : availableDevices) {
            try {
                device = MidiSystem.getMidiDevice(info);

                // Check to see if the device in an input device.
                // If it is, add it to the list.
                if(device.getMaxReceivers() != 0) deviceList.add(info);
            }
            catch(MidiUnavailableException e) {
                System.out.println("Input device is unavailable.  Not added to list\n");
                e.printStackTrace();
            }

        }

        // Return the array of MIDI Output Devices
        return deviceList.toArray(new MidiDevice.Info[0]);
    }

    // Get a specific MidiDevice (input) via String
    static MidiDevice getMidiInputDevice(String deviceName) {
        if(availableInputs == null) listAvailableMidiDevices();

        for(MidiDevice.Info info : availableInputs) {
            if(info.getName().equalsIgnoreCase(deviceName)) {
                try {
                    return MidiSystem.getMidiDevice(info);
                }
                catch(MidiUnavailableException e) {
                    System.out.println("MIDI Input device is unavailable\n");
                    e.printStackTrace();
                }
            }
        }

        System.out.println("No available input device found with the name " + deviceName + "!\n");
        return null;
    }

    // Get a specific MidiDevice (output) via String
    static MidiDevice getMidiOutputDevice(String deviceName) {
        if(availableOutputs == null) listAvailableMidiDevices();

        for(MidiDevice.Info info : availableOutputs) {
            if(info.getName().equalsIgnoreCase(deviceName)) {
                try {
                    return MidiSystem.getMidiDevice(info);
                }
                catch(MidiUnavailableException e) {
                    System.out.println("MIDI Output device is unavailable\n");
                    e.printStackTrace();
                }
            }
        }

        System.out.println("No available output device found with the name " + deviceName + "!\n");
        return null;
    }

    // Returns array of MidiDevice.Info[] objects of the available MIDI Devices
    static MidiDevice.Info[] getAvailableDevices() {
        if(availableDevices == null) listAvailableMidiDevices();

        return availableDevices;
    }

    static void printDeviceList() {
        System.out.println("Available Devices" +
                "=============================================");
        for (MidiDevice.Info info : availableDevices) {
            System.out.println(info.getName() + "\n" +
                               info.getDescription() + "\n" +
                               info.getVendor() + "\n" +
                               info.getVersion() + "\n" +
                               info.getClass() + "\n");
        }

        System.out.println("Available Inputs" +
                "=============================================");
        for (MidiDevice.Info info : availableInputs) {
            System.out.println(info.getName() + "\n" +
                               info.getDescription() + "\n" +
                               info.getVendor() + "\n" +
                               info.getVersion() + "\n" +
                               info.getClass() + "\n");
        }

        System.out.println("Available Outputs" +
                "=============================================");
        for (MidiDevice.Info info : availableOutputs) {
            System.out.println(info.getName() + "\n" +
                               info.getDescription() + "\n" +
                               info.getVendor() + "\n" +
                               info.getVersion() + "\n" +
                               info.getClass() + "\n");
        }
    }

}
