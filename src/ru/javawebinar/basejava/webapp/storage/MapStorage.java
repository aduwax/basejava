package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

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
    boolean isExist(String uuid) {
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
        return storage.get((String) searchKey);
    }

    @Override
    void deleteFromStorage(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    String getSearchKey(String uuid) {
        return uuid;
    }
}
