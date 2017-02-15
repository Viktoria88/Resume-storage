package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by viktoriyasidenko on 2/9/17.
 */
public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList();
    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        list.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getUuid().equals(uuid))
                return i;
        }
        return null;
    }

    @Override
    protected List<Resume> doGetAllSorted() {
//        return Collections.sort(list, RESUME_COMPARATOR);
        return null;
    }

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getFullName().compareTo(o2.getFullName());

}
