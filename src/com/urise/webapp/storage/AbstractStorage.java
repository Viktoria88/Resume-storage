package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Created by viktoriyasidenko on 1/27/17.
 */
public abstract class AbstractStorage implements Storage{


    public abstract void clear();

    public abstract void save(Resume r);

    protected abstract boolean usedKey(String uuid);

    public abstract void update(Resume r);

    public abstract Resume get(String uuid);

    public abstract void delete(String uuid);

    public abstract int size();



}
