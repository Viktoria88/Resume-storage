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
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void insertElement(Resume r, int index){

        int indexX = - index - 1;
        int numberCopy = size - indexX;
            System.arraycopy(storage, indexX, storage, indexX + 1, numberCopy);
            storage[indexX] = r;
    }

    protected void fillDeletedElement(int index){

        int numberCopy = size - index - 1;
        if(numberCopy > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberCopy);
        }
    }

}
