package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {
    @Override
    String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    void updateInStorage(Object uuid, Resume resume) {
        storage.put((String) uuid, resume);
    }

    @Override
    Resume getFromStorage(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    void deleteFromStorage(Object uuid) {
        storage.remove((String) uuid);
    }
}
