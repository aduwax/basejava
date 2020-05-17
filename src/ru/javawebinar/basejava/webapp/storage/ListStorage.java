package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public List<Resume> getAll() {
        return Arrays.asList(storage.toArray(new Resume[0]));
    }

    public int size() {
        return storage.size();
    }

    @Override
    boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    public void saveToStorage(Resume resume, Integer index) {
        storage.add(resume);
    }

    @Override
    void updateInStorage(Integer index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    Resume getFromStorage(Integer index) {
        return storage.get(index);
    }

    @Override
    Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    void deleteFromStorage(Integer index) {
        storage.remove(index.intValue());
    }

}
