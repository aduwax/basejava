package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public Resume get(String uuid) {
        LOG.info("Get storage: " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    public void update(Resume resume) {
        SK searchKey = getExistedSearchKey(resume.getUuid());
        updateInStorage(searchKey, resume);
    }


    public void delete(String uuid) {
        LOG.info("Delete storage: " + uuid);
        deleteFromStorage(getExistedSearchKey(uuid));
    }

    public void save(Resume resume) {
        LOG.info("Save storage: " + resume.toString());
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        saveToStorage(resume, searchKey);
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumeList = getAll();
        Collections.sort(resumeList);
        return resumeList;
    }

    abstract boolean isExist(SK searchKey);

    abstract void saveToStorage(Resume resume, SK searchKey);

    abstract void updateInStorage(SK searchKey, Resume resume);

    abstract Resume getFromStorage(SK searchKey);

    abstract void deleteFromStorage(SK searchKey);

    abstract SK getSearchKey(String uuid);

    public abstract List<Resume> getAll();
}