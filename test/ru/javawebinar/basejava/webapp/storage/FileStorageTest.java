package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.storage.strategy.ObjectStreamReadWriteStrategy;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamReadWriteStrategy()));
    }
}