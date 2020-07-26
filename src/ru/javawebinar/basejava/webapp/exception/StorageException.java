package ru.javawebinar.basejava.webapp.exception;

public class StorageException extends RuntimeException {
    private String uuid;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String uuid, String message) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String io_error, String name, Exception e) {
        super(io_error + " " + name, e);
    }

    public String getUuid() {
        return uuid;
    }
}