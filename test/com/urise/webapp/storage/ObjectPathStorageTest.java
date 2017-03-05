package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamSerialization;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerialization()));
    }
}