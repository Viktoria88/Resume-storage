package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.JsonStreamSerialization;
import com.urise.webapp.storage.serialization.XmlStreamSerialization;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerialization()));
    }
}