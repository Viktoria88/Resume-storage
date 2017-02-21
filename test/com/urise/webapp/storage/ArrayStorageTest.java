package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by viktoriyasidenko on 12/26/16.
 */
public class ArrayStorageTest extends AbstractArrayStorageTest {


    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

}