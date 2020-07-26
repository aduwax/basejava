package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.storage.strategy.ObjectStreamReadWriteStrategy;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamReadWriteStrategy()));
    }
}