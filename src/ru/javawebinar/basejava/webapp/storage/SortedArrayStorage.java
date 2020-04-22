package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume resume) {
        if (!isFull()) {
            int index = getIndex(resume.getUuid());
            if (index < 0 || !resume.equals(storage[index])) {
                int saveIndex = - (index + 1);
                int count = size - saveIndex;
                System.arraycopy(storage, saveIndex, storage, saveIndex + 1, count);
                storage[saveIndex] = resume;
                size++;
            } else {
                System.out.println("[ERROR] Resume '" + resume.getUuid() + "' already exists!");
            }
        } else {
            System.out.println("[ERROR] ArrayStorage is full!");
        }
    }

    @Override
    int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
