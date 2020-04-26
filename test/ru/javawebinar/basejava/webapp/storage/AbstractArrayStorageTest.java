package ru.javawebinar.basejava.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.exception.StorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected AbstractArrayStorageTest(Storage storage) {
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
                // Проверяем размер массива после очистки
                () -> Assertions.assertEquals(0, storage.size()),
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
        Assertions.assertArrayEquals(storage.getAll(), expectedStorage);
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
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void saveStorageOverflow() {
        storage.clear();
        Assertions.assertDoesNotThrow(() -> {
            final int storageLimit = AbstractArrayStorage.class.getDeclaredField("STORAGE_LIMIT").getInt(storage);
            for (int itemIndex = 0; itemIndex < storageLimit; itemIndex++) {
                // Наверное, не очень хорошая идея крутить в цикле 10000 объектов резюме,
                // может стоило поменять STORAGE_LIMIT и пересоздать массив через рефлексию или это тоже не очень?
                storage.save(new Resume());
            }
        });
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume(UUID_4)));
    }

    @Test
    void update() {
        // Этот тест не проходит для SortedArrayStorage, но я пока не понял почему
        // binarySearch возвращает -4, хотя объект есть в массиве
        final Resume resume = new Resume(UUID_4);
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.update(UUID_1, resume)),
                () -> Assertions.assertEquals(resume, storage.get(resume.getUuid())),
                () -> Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1))
        );
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(UUID_4, new Resume()));
    }

    @Test
    void delete() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> storage.delete(UUID_3)),
                () -> Assertions.assertEquals(2, storage.size()),
                () -> Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3))
        );
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }
}