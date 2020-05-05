package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        if (storageObjectExist(uuid)) {
            return getFromStorage(getIndex(uuid));
        }
        throw new NotExistStorageException(uuid);
    }

    public void update(Resume resume) {
        if (storageObjectExist(resume.getUuid())) {
            int index = getIndex(resume.getUuid());
            updateInStorage(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        if (storageObjectExist(uuid)) {
            deleteFromStorage(getIndex(uuid));
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

    private boolean storageObjectExist(String uuid) {
        return getIndex(uuid) >= 0;
    }

    abstract void saveToStorage(Resume resume);

    abstract void updateInStorage(int index, Resume resume);

    abstract Resume getFromStorage(int index);

    abstract void deleteFromStorage(int index);

    abstract int getIndex(String uuid);
}
