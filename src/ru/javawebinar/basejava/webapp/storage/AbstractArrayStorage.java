package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.exception.StorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    final static int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("[INFO] ArrayStorage cleaned. Size = 0");
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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public void save(Resume resume) {
        if (size <= STORAGE_LIMIT) {
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                putToArray(index, resume);
                size++;
            } else {
                throw new ExistStorageException(resume.getUuid());
            }
        } else {
            throw new StorageException(resume.getUuid(), "Failed to save " + resume.getUuid() + " - Storage overflow!");
        }
    }

    public void update(String uuid, Resume resume) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            int count = size - 1 - index;
            System.arraycopy(storage, index + 1, storage, index, count);
            storage[size - 1] = null;
            size--;
            System.out.println("[INFO] Resume '" + uuid + "' deleted");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    abstract int getIndex(String uuid);

    abstract void putToArray(int index, Resume resume);
}
