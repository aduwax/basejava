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
        exceptionIfNotExist(uuid);
        return getFromStorage(getSearchKey(uuid));
    }

    public void update(Resume resume) {
        LOG.info("Update storage: " + resume.toString());
        exceptionIfNotExist(resume.getUuid());
        SK searchKey = getSearchKey(resume.getUuid());
        updateInStorage(searchKey, resume);
    }

    public void delete(String uuid) {
        LOG.info("Delete storage: " + uuid);
        exceptionIfNotExist(uuid);
        deleteFromStorage(getSearchKey(uuid));
    }

    public void save(Resume resume) {
        LOG.info("Save storage: " + resume.toString());
        exceptionIfExist(resume.getUuid());
        saveToStorage(resume);
    }

    private void exceptionIfExist(String uuid){
        if (storageObjectExist(uuid)){
            throw new ExistStorageException(uuid);
        }
    }

    private void exceptionIfNotExist(String uuid){
        if (!storageObjectExist(uuid)){
            throw new NotExistStorageException(uuid);
        }
    }

    public List<Resume> getAllSorted(){
        Resume[] getAllArray = getAll();
        Arrays.sort(getAllArray);
        return Arrays.asList(getAllArray);
    }

    abstract boolean storageObjectExist(String uuid);

    abstract void saveToStorage(Resume resume);

    abstract void updateInStorage(SK searchKey, Resume resume);

    abstract Resume getFromStorage(SK searchKey);

    abstract void deleteFromStorage(SK searchKey);

    abstract SK getSearchKey(String uuid);

    abstract Resume[] getAll();
}
