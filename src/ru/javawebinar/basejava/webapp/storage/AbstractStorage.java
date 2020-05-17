package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public Resume get(String uuid) {
        LOG.info("Get storage: " + uuid);
        return getFromStorage(getSearchKeyIfExist(uuid));
    }

    public void update(Resume resume) {
        SK searchKey = getSearchKeyIfExist(resume.getUuid());
        updateInStorage(searchKey, resume);
    }


    public void delete(String uuid) {
        LOG.info("Delete storage: " + uuid);
        deleteFromStorage(getSearchKeyIfExist(uuid));
    }

    public void save(Resume resume) {
        LOG.info("Save storage: " + resume.toString());
        SK searchKey = getSearchKeyIfNotExist(resume.getUuid());
        saveToStorage(resume, searchKey);
    }

    private SK getSearchKeyIfNotExist(String uuid) {
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        } else {
            return getSearchKey(uuid);
        }
    }

    private SK getSearchKeyIfExist(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getSearchKey(uuid);
        }
    }

    public List<Resume> getAll() {
        Resume[] getAllArray = getAllAsArray();
        Arrays.sort(getAllArray);
        return Arrays.asList(getAllArray);
    }

    abstract boolean isExist(String uuid);

    abstract void saveToStorage(Resume resume, SK searchKey);

    abstract void updateInStorage(SK searchKey, Resume resume);

    abstract Resume getFromStorage(SK searchKey);

    abstract void deleteFromStorage(SK searchKey);

    abstract SK getSearchKey(String uuid);

    abstract Resume[] getAllAsArray();
}
