package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.StorageException;
import ru.javawebinar.basejava.webapp.model.Resume;
import ru.javawebinar.basejava.webapp.storage.strategy.StorageReadWriteStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;

    private final StorageReadWriteStrategy readWriteStrategy;

    protected FileStorage(String directory, StorageReadWriteStrategy readWriteStrategy) {
        File directoryFile = new File(directory);
        Objects.requireNonNull(directoryFile, "directory must not be null");

        this.readWriteStrategy = readWriteStrategy;
        if (!directoryFile.isDirectory()) {
            throw new IllegalArgumentException(directoryFile.getAbsolutePath() + " is not directory");
        }
        if (!directoryFile.canRead() || !directoryFile.canWrite()) {
            throw new IllegalArgumentException(directoryFile.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directoryFile;
    }

    @Override
    public void clear() {
        File[] files = getDirectoryFilesIfExists();
        for (File file : files) {
            deleteFromStorage(file);
        }
    }

    @Override
    public int size() {
        return getDirectoryFilesIfExists().length;
    }

    @Override
    boolean isExist(File file) {
        return file.exists();
    }

    @Override
    Resume getFromStorage(File file) {
        try {
            return readWriteStrategy.readFromStorage(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Fail to getFromStorage");
        }
    }

    @Override
    void saveToStorage(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("File save error", file.getAbsolutePath(), e);
        }
        updateInStorage(file, resume);
    }

    @Override
    void updateInStorage(File file, Resume resume) {
        try {
            readWriteStrategy.writeToStorage(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    void deleteFromStorage(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete failed", file.getAbsolutePath());
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public List<Resume> getAll() {
        File[] files = getDirectoryFilesIfExists();
        List<Resume> list = new ArrayList<>();
        for (File resumeFile : files) {
            list.add(getFromStorage(resumeFile));
        }
        return list;
    }

    private File[] getDirectoryFilesIfExists(){
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory reading fail", null);
        }
        return files;
    }
}