package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Created by viktoriyasidenko on 12/13/16.
 */
public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        Arrays.sort(storage, 0, size);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
