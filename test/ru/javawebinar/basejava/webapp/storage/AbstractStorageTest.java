package ru.javawebinar.basejava.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class AbstractStorageTest {
    protected final Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUpEach() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
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
                new Resume(UUID_1),
                new Resume(UUID_2),
                new Resume(UUID_3)
        };
        // Запихал возвращаемое storage.getAll в лист т.к. в мапе элементы массива возвращаются не в том порядке
        // (ищу индексы элементов expectedStorage в resumeList)
        List<Resume> resumeList = new ArrayList<>(Arrays.asList(storage.getAll()));
        for (Resume resume : expectedStorage) {
            Assertions.assertNotEquals(-1, resumeList.indexOf(resume));
        }
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void get() {
        Assertions.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
    }

    @Test
    void save() {
        final Resume resume = new Resume(UUID_4);
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.save(resume)),
                () -> Assertions.assertEquals(resume, storage.get(resume.getUuid()))
        );
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void update() {
        final Resume resume = new Resume(UUID_3);
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.update(resume)),
                () -> Assertions.assertEquals(resume, storage.get(resume.getUuid()))
        );
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(new Resume()));
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