package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        if (storageObjectExist(uuid)) {
            return getFromStorage(getSearchKey(uuid));
        }
        throw new NotExistStorageException(uuid);
    }

    public void update(Resume resume) {
        if (storageObjectExist(resume.getUuid())) {
            Object searchKey = getSearchKey(resume.getUuid());
            updateInStorage(searchKey, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        if (storageObjectExist(uuid)) {
            deleteFromStorage(getSearchKey(uuid));
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        if (!storageObjectExist(resume.getUuid())) {
            saveToStorage(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    abstract boolean storageObjectExist(String uuid);

    abstract void saveToStorage(Resume resume);

    abstract void updateInStorage(Object searchKey, Resume resume);

    abstract Resume getFromStorage(Object searchKey);

    abstract void deleteFromStorage(Object searchKey);

    abstract Object getSearchKey(String uuid);
}
