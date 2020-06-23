package ru.javawebinar.basejava.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        this(uuid, "Resume '" + uuid + "' already exists in storage");
    }

    public ExistStorageException(String uuid, String message) {
        super(uuid, message);
    }
}