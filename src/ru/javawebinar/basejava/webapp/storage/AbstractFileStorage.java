package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.StorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteFromStorage(file);
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list != null) {
            return list.length;
        } else {
            throw new StorageException("Directory reading fail", null);
        }
    }

    @Override
    boolean isExist(File file) {
        return file.exists();
    }

    @Override
    Resume getFromStorage(File file) {
        try {
            return readFromStorage(file);
        } catch (IOException e) {
            throw new StorageException("Fail to getFromStorage");
        }
    }

    @Override
    void saveToStorage(Resume resume, File file) {
        try {
            file.createNewFile();
            writeToStorage(resume, file);
        } catch (IOException e) {
            throw new StorageException("File save error", file.getAbsolutePath(), e);
        }
    }

    @Override
    void updateInStorage(File file, Resume resume) {
        writeToStorage(resume, file);
    }

    @Override
    void deleteFromStorage(File file) {
        if (!file.delete()){
            throw new StorageException("File delete failed", file.getAbsolutePath());
        };
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public List<Resume> getAll() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Fail to read storage directory");
        }

        List<Resume> list = new ArrayList<>();
        for (File resumeFile: files) {
            list.add(getFromStorage(resumeFile));
        }
        return list;
    }

    protected abstract void writeToStorage(Resume resume, File file);
    protected abstract Resume readFromStorage(File file) throws IOException;
}