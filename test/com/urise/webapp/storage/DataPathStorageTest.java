package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.DataStreamSerialization;
import com.urise.webapp.storage.serialization.ObjectStreamSerialization;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerialization()));
    }
}