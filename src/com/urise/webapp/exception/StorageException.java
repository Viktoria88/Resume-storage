package com.urise.webapp.exception;


/**
 * Created by viktoriyasidenko on 12/19/16.
 */
public class StorageException extends RuntimeException {

    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
