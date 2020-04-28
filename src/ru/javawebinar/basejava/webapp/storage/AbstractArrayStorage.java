package ru.javawebinar.basejava.webapp.storage;

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
        System.out.println("[ERROR] Resume '" + uuid + "' not found in storage, return null");
        return null;
    }

    public void save(Resume resume) {
        if (size <= STORAGE_LIMIT) {
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                saveToStorage(index, resume);
                size++;
            } else {
                System.out.println("[ERROR] Resume '" + resume.getUuid() + "' already exists!");
            }
        } else {
            System.out.println("[ERROR] ArrayStorage is full!");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("[ERROR] Resume '" + resume.getUuid() + "' not found in storage for update");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteFromStorage(index);
            storage[size - 1] = null;
            size--;
            System.out.println("[INFO] Resume '" + uuid + "' deleted");
        } else {
            System.out.println("[ERROR] Resume '" + uuid + "' not found in storage for delete");
        }
    }

    abstract void deleteFromStorage(int index);

    abstract int getIndex(String uuid);

    abstract void saveToStorage(int index, Resume resume);
}
