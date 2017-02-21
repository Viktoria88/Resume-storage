package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viktoriyasidenko on 1/27/17.
 */
public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    public void clear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object resume) {
        map.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    protected List<Resume> doCopyAll(){
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
