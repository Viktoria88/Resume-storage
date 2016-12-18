package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {

        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {

        int index = getIndex(r.getUuid());
        if (isFull()) {
            System.out.println("Storage resume crowded");
            return;
        } else if (index >= 0) {
            System.out.println("This resume with uuid " + r.getUuid() + " is already exists");
            return;
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void update(Resume r){
        int index = getIndex(r.getUuid());
        if (index < 0){
            System.out.println("Resume with uuid " + r.getUuid() + " don't find");
        } else {
            System.out.println("Enter your updating");
            storage[index] = r;
            return;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Resume with uuid " + uuid + " will be deleted");
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
            return;
        } else {
            System.out.println("Resume with uuid " + uuid + " don't find");
        }
    }

    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, size);

    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume with uuid " + uuid + " don't find");
            return null;
        }
    }

    private boolean isFull() {
        if (size == STORAGE_LIMIT) {
            return true;
        } else {
            return false;
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);


}
