package ru.javawebinar.basejava.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

class MapFullNameStorageTest extends AbstractStorageTest {
    MapFullNameStorageTest() {
        super(new MapFullNameStorage());
    }

    @Test
    @Override
    void get() {
        Assertions.assertEquals(new Resume(UUID_1, UUID_1_NAME), storage.get(UUID_1_NAME));
    }

    @Test
    @Override
    void save() {
        final Resume resume = new Resume(UUID_4);
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.save(resume)),
                () -> Assertions.assertEquals(resume, storage.get(resume.getFullName()))
        );
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    @Override
    void saveExist() {
        for (Resume resume:
             storage.getAllSorted()) {
            System.out.println(resume.toString());
        }
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1, UUID_1_NAME)));
    }

    @Test
    void update() {
        for (Resume resume:
                storage.getAllSorted()) {
            System.out.println(resume.toString());
        }
        final Resume resume = new Resume(UUID_3, UUID_3_NAME);
        System.out.println(resume.toString());
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.update(resume)),
                () -> Assertions.assertEquals(resume, storage.get(resume.getFullName()))
        );
    }

    @Test
    void delete() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.delete(UUID_3_NAME)),
                () -> Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3_NAME))
        );
        Assertions.assertEquals(2, storage.size());
    }

    //TODO: Переделать
    // 1. Код AbstractStorage таким образом, чтобы убрать привязку к uuid в методах
    // 2. Код тестов так, чтобы не пришлось их добавлять в MapFullNameStorageTest
    // 3. equals в Resume - см. задание
}