package ru.javawebinar.basejava.webapp.storage.serializer;

import ru.javawebinar.basejava.util.XmlParser;
import ru.javawebinar.basejava.webapp.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StreamSerializer {
    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(
                Resume.class,
                TextSection.class,
                TextListSection.class,
                TimelineSection.class,
                TimelineSectionRecord.class,
                TimelineSectionRecord.Period.class
        );
    }

    @Override
    public void writeToStorage(Resume resume, OutputStream outputStream) throws IOException {
        try (Writer w = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, w);
        }
    }

    @Override
    public Resume readFromStorage(InputStream inputStream) throws IOException {
        try (Reader r = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}