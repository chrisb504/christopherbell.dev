package dev.christopherbell.azurras.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class Constants {
    private final Log LOG = LogFactory.getLog(Constants.class);
    public final static String NULL_REQUEST = "ERROR: A Null Request was received.";
    public final static String ERROR_NULL_REQUEST = "CBBLOG: ERROR: A Null Request was received.";
    public final static String POST_ADDED_SUCCESS = "CBBLOG: Post Added Successfully";
    public final static String POST_ADDED_FAILURE = "CBBLOG: ERROR: Failed to Add Post";
    public final static String POST_DELETED_SUCCESS = "CBBLOG: Post Deleted Successfully";
    public final static String POST_DELETED_FAILURE = "CBBLOG: ERROR: Failed to Delete Post";
    public final static String STATUS_FAILURE = "Failure";
    public final static String STATUS_SUCCESS = "Success";

}