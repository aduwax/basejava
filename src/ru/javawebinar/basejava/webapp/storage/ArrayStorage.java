package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (!isFull()) {
            int index = getIndex(resume.getUuid());
            if (index < 0 || !resume.equals(storage[index])) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("[ERROR] Resume '" + resume.getUuid() + "' already exists!");
            }
        } else {
            System.out.println("[ERROR] ArrayStorage is full!");
        }
    }

    int getIndex(String uuid) {
        for (int index = 0; index < size; index++) {
            if (storage[index].getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }




}
