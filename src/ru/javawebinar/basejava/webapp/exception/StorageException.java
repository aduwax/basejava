package ru.javawebinar.basejava.webapp.exception;

import java.io.IOException;

public class StorageException extends RuntimeException {
    private String uuid;

    public StorageException(String uuid) {
        this.uuid = uuid;
    }

    public StorageException(String uuid, String message) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String io_error, String name, IOException e) {
        super(io_error + " " + name, e);
    }

    public String getUuid(){
        return uuid;
    }
}
