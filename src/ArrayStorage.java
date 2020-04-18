import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int index=0; index < size(); index++)
            storage[index] = null;
        size = 0;
    }

    void save(Resume r) {
        storage[size()] = r;
        size++;
    }

    Resume get(String uuid) {
        int itemIndex = this.getItemIndex(uuid);
        if (itemIndex >= 0){
            return storage[itemIndex];
        } return null;
    }

    void delete(String uuid) {
        int itemIndex = this.getItemIndex(uuid);
        if (itemIndex >= 0){
            Resume[] newStorage = new Resume[10000];
            // System.arraycopy(Источник[], int С какого эл-та копировать, Получатель[],
            //                  int С какого эл-та вставлять, int Сколько эл-тов)
            System.arraycopy(storage, 0, newStorage, 0, itemIndex);
            System.arraycopy(storage, itemIndex + 1, newStorage, itemIndex, size() -1);
            storage = newStorage;
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

    int getItemIndex(String uuid){
        for (int index=0; index < size(); index++) {
            if (storage[index].uuid.equals(uuid)) {
                return index;
            }
        } return -1;
    }
}
