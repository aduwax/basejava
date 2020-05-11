package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        return getFromStorage(getSearchKeyIfExist(uuid));
    }

    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid());
        updateInStorage(searchKey, resume);
    }

    public void delete(String uuid) {
        deleteFromStorage(getSearchKeyIfExist(uuid));
    }

    public void save(Resume resume) {
        getSearchKeyIfNotExist(resume.getUuid());
        saveToStorage(resume);
    }

    private Object getSearchKeyIfNotExist(String uuid){
        if (isExist(uuid)){
            throw new ExistStorageException(uuid);
        } else {
            return getSearchKey(uuid);
        }
    }

    private Object getSearchKeyIfExist(String uuid){
        if (!isExist(uuid)){
            throw new NotExistStorageException(uuid);
        } else {
            return getSearchKey(uuid);
        }
    }

    public List<Resume> getAllSorted(){
        Resume[] getAllArray = getAll();
        Arrays.sort(getAllArray);
        return Arrays.asList(getAllArray);
    }

    abstract boolean isExist(String uuid);

    abstract void saveToStorage(Resume resume);

    abstract void updateInStorage(Object searchKey, Resume resume);

    abstract Resume getFromStorage(Object searchKey);

    abstract void deleteFromStorage(Object searchKey);

    abstract Object getSearchKey(String uuid);

    abstract Resume[] getAll();
}
