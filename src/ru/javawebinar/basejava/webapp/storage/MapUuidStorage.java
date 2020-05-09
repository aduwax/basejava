package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {
    @Override
    Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
