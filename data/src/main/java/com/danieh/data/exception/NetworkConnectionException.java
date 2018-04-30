package com.danieh.data.exception;

/**
 * Exception throw by the application when a there is a network connection exception.
 */
public class NetworkConnectionException extends Exception {

    public NetworkConnectionException() {
        super();
    }

    public NetworkConnectionException(final Throwable cause) {
        super(cause);
    }

    public NetworkConnectionException(String s) {
        super(s);
    }
}
