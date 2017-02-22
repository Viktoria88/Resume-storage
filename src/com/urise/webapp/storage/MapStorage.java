package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Created by viktoriyasidenko on 1/27/17.
 */
public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> map = new HashMap<>();

    public void clear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r, String uuid) {
        map.put(uuid, r);
    }

    @Override
    protected void doSave(Resume r, String uuid) {
        map.put(uuid, r);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    protected List<Resume> doCopyAll(){
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
