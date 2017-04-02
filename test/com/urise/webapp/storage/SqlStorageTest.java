package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.storage.serialization.DataStreamSerialization;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getStorageDir()));
    }
}