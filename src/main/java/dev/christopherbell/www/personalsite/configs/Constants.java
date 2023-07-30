package dev.christopherbell.www.personalsite.configs;


public final class Constants {
    private Constants() {
        throw new IllegalStateException("Config Constant class is not allowed to be instanced");
    }

    public static final String NULL_REQUEST = "ERROR: A Null Request was received.";
    public static final String ERROR_NULL_REQUEST = "CBBLOG: ERROR: A Null Request was received.";
    public static final String POST_ADDED_SUCCESS = "CBBLOG: Post Added Successfully";
    public static final String POST_ADDED_FAILURE = "CBBLOG: ERROR: Failed to Add Post";
    public static final String POST_DELETED_SUCCESS = "CBBLOG: Post Deleted Successfully";
    public static final String POST_DELETED_FAILURE = "CBBLOG: ERROR: Failed to Delete Post";
    public static final String STATUS_FAILURE = "Failure";
    public static final String STATUS_SUCCESS = "Success";
}