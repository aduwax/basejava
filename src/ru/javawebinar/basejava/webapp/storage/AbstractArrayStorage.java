package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.StorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
            saveItem(getSearchKey(resume.getUuid()), resume);
            size++;
        } else {
            throw new StorageException(resume.getUuid(), "Failed to save " + resume.getUuid() + " - Storage overflow!");
        }
    }

    @Override
    void updateInStorage(Integer index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    Resume getFromStorage(Integer index) {
        return storage[index];
    }

    @Override
    void deleteFromStorage(Integer index) {
        deleteItem(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    boolean storageObjectExist(String uuid) {
        return getSearchKey(uuid) >= 0;
    }

    abstract void deleteItem(int index);

    abstract Integer getSearchKey(String uuid);

    abstract void saveItem(int index, Resume resume);
}
