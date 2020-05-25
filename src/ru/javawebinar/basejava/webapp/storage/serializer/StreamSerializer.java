package ru.javawebinar.basejava.webapp.storage.serializer;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    void writeToStorage(Resume r, OutputStream os) throws IOException;
    Resume readFromStorage(InputStream is) throws IOException;
}
