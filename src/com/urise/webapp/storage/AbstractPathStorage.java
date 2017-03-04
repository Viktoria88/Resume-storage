package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by viktoriyasidenko on 3/1/17.
 */
public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private Path directory;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;


    protected AbstractPathStorage(String dir){
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if(!Files.isDirectory(directory) || Files.isWritable(directory)){
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }
    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error ", null);
        }
    }

    @Override
    public int size() {
        try {
            Files.size(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
        return size();
    }

    @Override
    protected void doUpdate(Resume r, Path Path) {
        try {
            doWrite(r, new BufferedOutputStream(Files.newOutputStream(directory)));
        } catch (IOException e) {
            throw new StorageException("Path write error", directory.getFileName().toString(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path Path) {
        try {
            Files.createFile(directory);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path ", directory.getFileName().toString(), e);
        }
        doUpdate(r, directory);
    }

    @Override
    protected void doDelete(Path Path) {
        try {
            Files.delete(directory);
        } catch (IOException e) {
           throw new StorageException("Path delete error", directory.getFileName().toString());
        }
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(directory)));
        } catch (IOException e) {
            throw new StorageException("Path read error ", directory.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path Path) {
        return Files.exists(directory);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return null; //Files.getAttribute(directory, uuid);
    }

    @Override
    protected List<Resume> doCopyAll() {

//        Path[] list = new Path[];
//        Files.copy(directory, list);
        return null;
    }
}
