package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by viktoriyasidenko on 12/13/16.
 */
public class SortedArrayStorage extends AbstractArrayStorage {

//    private static class ResumeComparator implements Comparator<Resume>{
//
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            return o1.getUuid().compareTo(o2.getUuid());
//        }
//    }

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
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
