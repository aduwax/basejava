package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    void saveToStorage(int index, Resume resume) {
        int saveIndex = -index - 1;
        int count = size - saveIndex;
        System.arraycopy(storage, saveIndex, storage, saveIndex + 1, count);
        storage[saveIndex] = resume;
    }

    @Override
    void deleteFromStorage(int index) {
        int count = size - 1 - index;
        System.arraycopy(storage, index + 1, storage, index, count);
    }

    @Override
    int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    void deleteItem(int index) {
        int count = size - 1 - index;
        System.arraycopy(storage, index + 1, storage, index, count);
    }
}
