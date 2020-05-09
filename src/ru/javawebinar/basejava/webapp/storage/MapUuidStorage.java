package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage<String> {
    @Override
    String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    void updateInStorage(String uuid, Resume resume) {
        storage.put(uuid, resume);
    }

    @Override
    Resume getFromStorage(String uuid) {
        return storage.get(uuid);
    }

    @Override
    void deleteFromStorage(String uuid) {
        storage.remove(uuid);
    }
}
