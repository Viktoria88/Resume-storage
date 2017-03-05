package com.urise.webapp.storage;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.getAbsolutePath()));
    }
}