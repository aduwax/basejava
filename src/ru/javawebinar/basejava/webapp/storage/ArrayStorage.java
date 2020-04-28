package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    void putToArray(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    void updateItem(int index, Resume resume) {

    }

    @Override
    Resume getItem(int index) {
        return null;
    }

    int getIndex(String uuid) {
        for (int index = 0; index < size; index++) {
            if (storage[index].getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    void deleteItem(int index) {
        int count = size - 1 - index;
        System.arraycopy(storage, index + 1, storage, index, count);
        storage[size - 1] = null;
    }


}
