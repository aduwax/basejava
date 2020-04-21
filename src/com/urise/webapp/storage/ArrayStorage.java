package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final static int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
        System.out.println("[INFO] ArrayStorage cleaned. Size = 0");
    }

    public void save(Resume r) {
        if (!isFull()) {
            int index = getIndex(r.getUuid());
            if (index == -1 || !r.equals(storage[index])) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("[ERROR] Resume '" + r.getUuid() + "' already exists!");
            }
        } else {
            System.out.println("[ERROR] ArrayStorage is full!");
        }
    }

    public void update(String uuid, Resume resume) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("[ERROR] Resume '" + uuid + "' not found in storage for update");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("[ERROR] Resume '" + uuid + "' not found in storage, return null");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            int count = size - 1 - index;
            System.arraycopy(storage, index + 1, storage, index, count);
            if (isFull()) {
                storage[STORAGE_LIMIT - 1] = null;
            }
            size--;
            System.out.println("[INFO] Resume '" + uuid + "' deleted");
        } else {
            System.out.println("[ERROR] Resume '" + uuid + "' not found in storage for delete");
        }
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

    int getIndex(String uuid) {
        for (int index = 0; index < size; index++) {
            if (storage[index].getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    boolean isFull() {
        return size >= STORAGE_LIMIT;
    }


}
