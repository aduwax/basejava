package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    HashMap<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return (storage.values().toArray(new Resume[0]));
    }

    public int size() {
        return storage.size();
    }

    @Override
    boolean storageObjectExist(String uuid) {
        return storage.get(uuid) != null;
    }

    @Override
    void saveToStorage(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    void updateInStorage(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    Resume getFromStorage(Object searchKey) {
        Resume resumeSearchKey = (Resume) searchKey;
        return storage.get(resumeSearchKey.getUuid());
    }

    @Override
    void deleteFromStorage(Object searchKey) {
        Resume resumeSearchKey = (Resume) searchKey;
        storage.remove(resumeSearchKey.getUuid());
    }

    @Override
    Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
