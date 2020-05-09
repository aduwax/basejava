package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Map;

public class MapFullNameStorage extends AbstractMapStorage {
    @Override
    boolean storageObjectExist(String fullName) {
        return getSearchKey(fullName) != null;
    }

    @Override
    Resume getSearchKey(String fullName) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getFullName().equals(fullName)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void update(Resume resume) {
        if (storageObjectExist(resume.getFullName())) {
            Object searchKey = getSearchKey(resume.getFullName());
            updateInStorage(searchKey, resume);
        } else {
            throw new NotExistStorageException(resume.getFullName());
        }
    }

    public void save(Resume resume) {
        if (!storageObjectExist(resume.getFullName())) {
            saveToStorage(resume);
        } else {
            throw new ExistStorageException(resume.getFullName());
        }
    }
}
