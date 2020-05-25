package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.webapp.Config;

class SqlStorageTest extends AbstractStorageTest {
    SqlStorageTest() {
        super(new SqlStorage(
                Config.get().getDbUrl(),
                Config.get().getDbUser(),
                Config.get().getDbPassword()
        ));
        ResumeTestData.makeSimpleResume = true;
    }
}