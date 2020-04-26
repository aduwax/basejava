package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    void putToArray(int index, Resume resume) {
        int saveIndex = -(index + 1);
        int count = size - saveIndex;
        System.arraycopy(storage, saveIndex, storage, saveIndex + 1, count);
        storage[saveIndex] = resume;
    }

    int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
