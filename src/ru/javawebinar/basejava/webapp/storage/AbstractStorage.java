package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage{

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return getItem(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public void update(String uuid, Resume resume) {
        int index = getIndex(uuid);
        if (index >= 0) {
            updateItem(index, resume);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteItem(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public abstract void save(Resume resume);

    abstract void updateItem(int index, Resume resume);

    abstract Resume getItem(int index);

    abstract int getIndex(String uuid);

    abstract void deleteItem(int index);
}
