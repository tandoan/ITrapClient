package com.example.TrapProtocol;

public class TrapProtocol {
	// Message types for the trap protocol

    public static final int COMMAND_REQUEST_CONFIGURATION = 1;
    public static final int COMMAND_SENDING_CONFIGURATION = 2;
    public static final int COMMAND_SET_CONFIGURATION = 3;
    public static final int COMMAND_CONFIGURATION_SUCCESSFUL = 4;
    public static final int COMMAND_CONFIGURATION_FAILURE = 5;
    public static final int COMMAND_SET_SERVER_TIME = 6;
    public static final int COMMAND_SERVER_TIME_SET_SUCESSFUL = 6;
    public static final int COMMAND_SERVER_TIME_SET_FAILURE = 7;
    public static final int COMMAND_REQUEST_IMAGE = 8;
    public static final int COMMAND_IMAGE_RECEIVED_SUCCESSFULLY = 9;
    public static final int COMMAND_DELETE_IMAGE = 10;
    public static final int COMMAND_IMAGE_DELETED_SUCCESSFULLY = 11;
    public static final int COMMAND_IMAGE_DELETED_FAILURE = 12;
}
