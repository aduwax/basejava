package ru.javawebinar.basejava;

import ru.javawebinar.basejava.webapp.model.Resume;
import ru.javawebinar.basejava.webapp.storage.ArrayStorage;
import ru.javawebinar.basejava.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Unknown Name");
        Resume r2 = new Resume("uuid2", "Unknown Name");
        Resume r3 = new Resume("uuid3", "Unknown Name");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        String updatedUuid = r2.getUuid().concat("_updated");
        ARRAY_STORAGE.update(new Resume(updatedUuid, "Unknown Person"));
        ARRAY_STORAGE.update(r2);
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
