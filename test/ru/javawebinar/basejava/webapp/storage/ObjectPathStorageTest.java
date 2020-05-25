package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.storage.strategy.StorageObjectStreamReadWriteStrategy;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new StorageObjectStreamReadWriteStrategy()));
    }
}