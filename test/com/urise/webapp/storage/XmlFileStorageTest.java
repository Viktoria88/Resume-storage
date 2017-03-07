package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamSerialization;
import com.urise.webapp.storage.serialization.XmlStreamSerialization;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class XmlFileStorageTest extends AbstractStorageTest {

    public XmlFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new XmlStreamSerialization()));
    }
}