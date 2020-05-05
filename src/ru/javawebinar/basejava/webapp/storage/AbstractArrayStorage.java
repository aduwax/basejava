package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.StorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    final static int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void saveToStorage(Resume resume) {
        if (size < STORAGE_LIMIT) {
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                saveItem(index, resume);
                size++;
            } else {
                throw new ExistStorageException(resume.getUuid());
            }
        } else {
            throw new StorageException(resume.getUuid(), "Failed to save " + resume.getUuid() + " - Storage overflow!");
        }
    }

    void deleteFromStorage(int index){
        deleteItem(index);
        storage[size - 1] = null;
        size--;
    }

    abstract void deleteItem(int index);

    @Override
    Resume getFromStorage(int index) {
        return storage[index];
    }

    @Override
    protected void updateInStorage(int index, Resume resume){
        storage[index] = resume;
    }

    abstract int getIndex(String uuid);

    abstract void saveItem(int index, Resume resume);
}
