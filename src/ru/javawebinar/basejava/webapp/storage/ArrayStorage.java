package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    void saveItem(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    void deleteItem(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    Integer getSearchKey(String uuid) {
        for (int index = 0; index < size; index++) {
            if (storage[index].getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }
}