package com.klm.api;

public interface APIMessages {
    public static String FLIGHT_ADDED_SUCCESSFULLY = "Flight added successfully";
    public static String FLIGHT_NOT_FOUND = "Flight not found!";
    public static String MEALS_ADDED_SUCCESSFULLY = "Meals added successfully";
    public static String FLIGHT_ALREADY_EXIST = "Unable to add; Flight already exist";
    public static String MALFORMED_REQUEST = "Malformed Request format, please check once";
    public static String UNEXCPECTED_DB_ERROR = "Unable to perform the requested operation due to unexpected database exception.";
    public static String FLIGHT_DELETED = "Flight %s Deleted";
}
