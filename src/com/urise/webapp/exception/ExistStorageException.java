package com.urise.webapp.exception;

/**
 * Created by viktoriyasidenko on 12/19/16.
 */
public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }
}
