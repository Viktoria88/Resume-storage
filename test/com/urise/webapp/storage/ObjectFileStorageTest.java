package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamSerialization;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}