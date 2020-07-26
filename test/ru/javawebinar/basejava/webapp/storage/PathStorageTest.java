package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.webapp.storage.strategy.ObjectStreamReadWriteStrategy;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamReadWriteStrategy()));
    }
}