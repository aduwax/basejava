package ru.javawebinar.basejava.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        this(uuid, "Resume '" + uuid + "' not found in storage");
    }

    public NotExistStorageException(String uuid, String message) {
        super(uuid, message);
    }
}