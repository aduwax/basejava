package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {
    @Override
    Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    void updateInStorage(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    Resume getFromStorage(Object searchKey) {
        Resume resumeSearchKey = (Resume) searchKey;
        return storage.get(resumeSearchKey.getUuid());
    }

    @Override
    void deleteFromStorage(Object searchKey) {
        Resume resumeSearchKey = (Resume) searchKey;
        storage.remove(resumeSearchKey.getUuid());
    }
}
