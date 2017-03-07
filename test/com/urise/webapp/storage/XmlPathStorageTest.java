package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamSerialization;
import com.urise.webapp.storage.serialization.XmlStreamSerialization;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerialization()));
    }
}