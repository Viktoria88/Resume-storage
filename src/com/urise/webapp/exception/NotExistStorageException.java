package com.urise.webapp.exception;

/**
 * Created by viktoriyasidenko on 12/19/16.
 */
public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
