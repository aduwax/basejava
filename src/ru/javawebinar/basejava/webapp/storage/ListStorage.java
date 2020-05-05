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

    public void saveToStorage(Resume resume) {
        storage.add(resume);
    }

    @Override
    void updateInStorage(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    Resume getFromStorage(int index) {
        return storage.get(index);
    }

    @Override
    int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    void deleteFromStorage(int index) {
        storage.remove(index);
    }

}
