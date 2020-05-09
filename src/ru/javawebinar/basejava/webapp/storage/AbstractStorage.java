package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        exceptionIfNotExist(uuid);
        return getFromStorage(getSearchKey(uuid));
    }

    public void update(Resume resume) {
        exceptionIfNotExist(resume.getUuid());
        Object searchKey = getSearchKey(resume.getUuid());
        updateInStorage(searchKey, resume);
    }

    public void delete(String uuid) {
        exceptionIfNotExist(uuid);
        deleteFromStorage(getSearchKey(uuid));
    }

    public void save(Resume resume) {
        exceptionIfExist(resume.getUuid());
        saveToStorage(resume);
    }

    private void exceptionIfExist(String uuid){
        if (storageObjectExist(uuid)){
            throw new ExistStorageException(uuid);
        }
    }

    private void exceptionIfNotExist(String uuid){
        if (!storageObjectExist(uuid)){
            throw new NotExistStorageException(uuid);
        }
    }

    abstract boolean storageObjectExist(String uuid);

    abstract void saveToStorage(Resume resume);

    abstract void updateInStorage(Object searchKey, Resume resume);

    abstract Resume getFromStorage(Object searchKey);

    abstract void deleteFromStorage(Object searchKey);

    abstract Object getSearchKey(String uuid);
}
