package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage<SK> extends AbstractStorage<SK> {
    protected final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    public List<Resume> getAll() {
        return Arrays.asList(storage.values().toArray(new Resume[0]));
    }

    public int size() {
        return storage.size();
    }

    @Override
    void saveToStorage(Resume resume, SK searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    abstract SK getSearchKey(String searchKey);
}
