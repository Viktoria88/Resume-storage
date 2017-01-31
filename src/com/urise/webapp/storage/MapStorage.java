package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by viktoriyasidenko on 1/27/17.
 */
public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap<>();

    public void clear(){
        storage.clear();
    }

    public void save(Resume r){
        if(!usedKey(r.getUuid())){
            throw new ExistStorageException(r.getUuid());
        } else {
            storage.put(r.getUuid(), r);
        }
    }

    protected boolean usedKey(String uuid){
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            uuid.equals(entry.getKey());
        }
        return true;
    }

    public void update(Resume r){
        if(!usedKey(r.getUuid())){
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage.put(r.getUuid(), r);
        }
    }

    public Resume get(String uuid) {
        if (!usedKey(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            for (Map.Entry<String, Resume> entry : storage.entrySet()) {
                return entry.getValue();
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
//        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
//            return (Map<String, Resume>)entry;
//        }
        return null;
    }

    public int size(){
        return storage.size();
    }

}
