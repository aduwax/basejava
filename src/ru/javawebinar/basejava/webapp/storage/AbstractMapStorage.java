package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMapStorage<SK> extends AbstractStorage<SK> {
    protected final Map<String, Resume> storage = new HashMap<>();

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
    abstract SK getSearchKey(String searchKey);
}
