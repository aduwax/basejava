import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int storageLimit = 10000;
    Resume[] storage = new Resume[storageLimit];
    int size = 0;

    void clear() {
        for (int index = 0; index < size; index++) {
            storage[index] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (!isFull()) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            int count = storage.length - 1 - index;
            System.arraycopy(storage, index + 1, storage, index, count);
            if (isFull()) {
                storage[storageLimit - 1] = null;
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
        return size;
    }

    int getIndex(String uuid) {
        for (int index = 0; index < size; index++) {
            if (storage[index].uuid.equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    boolean isFull() {
        return size >= storageLimit;
    }
}
