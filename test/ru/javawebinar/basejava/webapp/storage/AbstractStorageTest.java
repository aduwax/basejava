package ru.javawebinar.basejava.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;
import ru.javawebinar.basejava.ResumeTestData;

import java.util.Arrays;

abstract class AbstractStorageTest {
    protected final Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final String UUID_1_NAME = "uuid1_name";
    protected static final String UUID_2_NAME = "uuid2_name";
    protected static final String UUID_3_NAME = "uuid3_name";
    protected static final String UUID_4_NAME = "uuid4_name";

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUpEach() {
        storage.clear();
        storage.save(ResumeTestData.createInstance(UUID_1, UUID_1_NAME));
        storage.save(ResumeTestData.createInstance(UUID_2, UUID_2_NAME));
        storage.save(ResumeTestData.createInstance(UUID_3, UUID_3_NAME));
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertAll(
                // Проверяем, что через get больше элементы мы найти не можем
                () -> Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1)),
                () -> Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_2)),
                () -> Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3))
        );
    }

    @Test
    void getAll() {
        final Resume[] expectedStorage = new Resume[]{
                ResumeTestData.createInstance(UUID_1, UUID_1_NAME),
                ResumeTestData.createInstance(UUID_2, UUID_2_NAME),
                ResumeTestData.createInstance(UUID_3, UUID_3_NAME)
        };
        Arrays.sort(expectedStorage);
        Assertions.assertArrayEquals(expectedStorage, storage.getAll().toArray(new Resume[0]));
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void get() {
        Assertions.assertEquals(ResumeTestData.createInstance(UUID_1, UUID_1_NAME), storage.get(UUID_1));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
    }

    @Test
    void save() {
        final Resume resume = ResumeTestData.createInstance(UUID_4, "Unknown name");
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.save(resume)),
                () -> Assertions.assertEquals(resume, storage.get(resume.getUuid()))
        );
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(ResumeTestData.createInstance(UUID_1, UUID_1_NAME)));
    }

    @Test
    void update() {
        final Resume resume = ResumeTestData.createInstance(UUID_3, UUID_3_NAME);
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.update(resume)),
                () -> Assertions.assertEquals(resume, storage.get(resume.getUuid()))
        );
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(ResumeTestData.createInstance("not-found", "unknown")));
    }

    @Test
    void delete() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.delete(UUID_3)),
                () -> Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3))
        );
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }
}