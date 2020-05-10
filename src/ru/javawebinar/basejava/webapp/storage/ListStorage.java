package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return (storage.toArray(new Resume[0]));
    }

    public int size() {
        return storage.size();
    }

    @Override
    boolean isExist(String uuid) {
        return getSearchKey(uuid) != null;
    }

    public void saveToStorage(Resume resume) {
        storage.add(resume);
    }

    @Override
    void updateInStorage(Object index, Resume resume) {
        storage.set((Integer) index, resume);
    }

    @Override
    Resume getFromStorage(Object index) {
        return storage.get((Integer) index);
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
    void deleteFromStorage(Object index) {
        storage.remove(((Integer) index).intValue());
    }

}
