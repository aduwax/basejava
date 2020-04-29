package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return (storage.toArray(new Resume[0]));
    }

    public int size() {
        return storage.size();
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            storage.add(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    void updateItem(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    Resume getItem(int index) {
        return storage.get(index);
    }

    @Override
    int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    void deleteItem(int index) {
        storage.remove(index);
    }

}
