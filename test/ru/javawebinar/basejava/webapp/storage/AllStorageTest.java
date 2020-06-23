package ru.javawebinar.basejava.webapp.storage;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

// Для успешной компиляции необходимо дополнительно подключить в либы:
// https://mvnrepository.com/artifact/org.junit.platform/junit-platform-runner/1.4.2
// https://mvnrepository.com/artifact/org.junit.platform/junit-platform-launcher/1.4.2
// https://mvnrepository.com/artifact/org.junit.platform/junit-platform-suite-api/1.4.2
// https://i.imgur.com/K1cBqe9.png - на всякий случай, скриншот с зависимостями (junit5)

@RunWith(JUnitPlatform.class)
@SelectClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class
})

public class AllStorageTest {
}