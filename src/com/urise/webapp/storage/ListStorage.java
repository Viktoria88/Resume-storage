package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> list = new ArrayList();
    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        list.set(searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        list.add(r);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove((searchKey).intValue());
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getUuid().equals(uuid))
                return i;
        }
        return null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

}
