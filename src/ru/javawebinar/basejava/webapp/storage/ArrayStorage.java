package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

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



    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            int count = size - 1 - index;
            System.arraycopy(storage, index + 1, storage, index, count);
            storage[size - 1] = null;
            size--;
            System.out.println("[INFO] Resume '" + uuid + "' deleted");
        } else {
            System.out.println("[ERROR] Resume '" + uuid + "' not found in storage for delete");
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

    private boolean isFull() {
        return size >= STORAGE_LIMIT;
    }


}
