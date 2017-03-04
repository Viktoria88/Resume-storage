package com.urise.webapp.storage;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}