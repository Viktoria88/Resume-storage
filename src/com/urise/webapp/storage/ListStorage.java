package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.TreeSet;

/**
 * Created by viktoriyasidenko on 1/27/17.
 */
public class ListStorage extends AbstractStorage {

    protected TreeSet<String> storage = new TreeSet<>();

    public void clear(){
        storage.clear();
    }

    public void save(Resume r){
        if(usedKey(r.getUuid())){
            throw new ExistStorageException(r.getUuid());
        } else {
            storage.add(r.getUuid());
        }
    }

    protected boolean usedKey(String uuid){
        for (String r : storage) {
            uuid.equals(r);
        }
        return true;
    }

    public void update(Resume r){
        if(!usedKey(r.getUuid())){
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage.add(r.getUuid());
        }
    }

    public Resume get(String uuid) {
        if (!usedKey(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            for (String r : storage) {
//                return r;
            }
        }
        return null;
    }

    public void delete(String uuid){
        if (!usedKey(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            storage.remove(uuid);
        }
    }

    public Resume[] getAll(){
        return null;
    }

    public int size(){
        return storage.size();
    }


}
