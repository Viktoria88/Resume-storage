package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialization.StreamSerialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by viktoriyasidenko on 3/1/17.
 */
public class PathStorage extends AbstractStorage<Path> {

    private Path directory;

    private StreamSerialization streamSerialization;

    protected PathStorage(String dir, StreamSerialization streamSerialization){
        Objects.requireNonNull(dir, "directory must not be null");

        this.streamSerialization = streamSerialization;
        directory = Paths.get(dir);
        if(!Files.isDirectory(directory) || Files.isWritable(directory)){
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }
    @Override
    public void clear() {
            getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            streamSerialization.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", getFileName(path), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + getFileName(path), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
           throw new StorageException("Path delete error", getFileName(path), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamSerialization.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error ", getFileName(path), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected List<Resume> doCopyAll() {
       return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    private String getFileName(Path path){
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList(){
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path read error ", e);
        }
    }
}
