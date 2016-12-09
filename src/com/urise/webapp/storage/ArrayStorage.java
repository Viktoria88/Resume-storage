package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {

    private static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {

        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {

        if (isFull()) {
            System.out.println("Storage resume crowded");
            return;
        } else if (find(r.getUuid()) >= 0) {
            System.out.println("This resume is already exists");
            return;
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r){
        int index = find(r.getUuid());
        if (index < 0){
            System.out.println("Resume with uuid " + r.getUuid() + " don't find");
        } else {
            System.out.println("Enter your updating");
            storage[index] = r;
            return;
        }
    }

    public Resume get(String uuid) {
        int index = find(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume with uuid " + uuid + " don't find");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = find(uuid);
        if (index >= 0) {
            System.out.println("del" + index);
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        } else {
            System.out.println("Resume with uuid " + uuid + " don't find");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, size);

    }

    public int size() {
        return size;
    }

    private boolean isFull() {
        if (size == STORAGE_LIMIT) {
            return true;
        } else {
            return false;
        }
    }

    private int find(String uuidFound) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuidFound)) {
                return i;
            }
        }
        return -1;
    }
}
