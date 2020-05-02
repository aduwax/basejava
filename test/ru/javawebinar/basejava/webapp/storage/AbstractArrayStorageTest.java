package ru.javawebinar.basejava.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.webapp.exception.StorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void saveStorageOverflow() {
        storage.clear();
        Assertions.assertDoesNotThrow(() -> {
            final int storageLimit = AbstractArrayStorage.class.getDeclaredField("STORAGE_LIMIT").getInt(storage);
            for (int itemIndex = 0; itemIndex < storageLimit; itemIndex++) {
                storage.save(new Resume());
            }
        });
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume(UUID_4)));
    }
}