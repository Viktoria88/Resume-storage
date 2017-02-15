package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viktoriyasidenko on 1/27/17.
 */
public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            return entry.getValue();
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected String getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            uuid.equals(entry.getKey());
            return entry.getKey();
        }
        return null;
    }

    protected List<Resume> doGetAllSorted(){
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
//            return (Map<String, Resume>)entry;
        }
        return null;
    }
    public Resume[] getAll() {
        return null;
    }


    @Override
    public int size() {
        return 0;
    }
}
