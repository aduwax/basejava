package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.storage.strategy.StorageObjectStreamReadWriteStrategy;

public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new StorageObjectStreamReadWriteStrategy()));
    }
}