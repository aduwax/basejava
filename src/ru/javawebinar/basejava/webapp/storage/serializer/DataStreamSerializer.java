package ru.javawebinar.basejava.webapp.storage.serializer;

import ru.javawebinar.basejava.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void writeToStorage(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dataOutputStream.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dataOutputStream.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                writeSection(dataOutputStream, entry.getKey(), entry.getValue());
            }
        }
    }

    private void writeSection(DataOutputStream dataOutputStream,
                              SectionType sectionType, AbstractSection section) throws IOException {
        dataOutputStream.writeUTF(sectionType.name());
        dataOutputStream.writeUTF(sectionType.getTitle());
        switch (sectionType) {
            // Text
            case PERSONAL:
            case OBJECTIVE:
                writeTextSection(dataOutputStream, (TextSection) section);
                break;
            // TextList
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                writeTextListSection(dataOutputStream, (TextListSection) section);
                break;
            // TimeLine
            case EXPERIENCE:
            case EDUCATION:
                writeTimelineSection(dataOutputStream, (TimelineSection) section);
        }
    }

    private AbstractSection readSection(DataInputStream dataInputStream, SectionType sectionType) throws IOException {
        String title = dataInputStream.readUTF();
        switch (sectionType) {
            // Text
            case PERSONAL:
            case OBJECTIVE:
                return readTextSection(dataInputStream, title);
            // TextList
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return readTextListSection(dataInputStream, title);
            // TimeLine
            case EXPERIENCE:
            case EDUCATION:
                return readTimelineSection(dataInputStream, title);
            default:
                throw new IllegalStateException("Unexpected value: " + sectionType);
        }
    }

    private TextSection readTextSection(DataInputStream dataInputStream, String title) throws IOException {
        TextSection textSection = new TextSection(title);
        textSection.setText(dataInputStream.readUTF());
        return textSection;
    }

    private TextListSection readTextListSection(DataInputStream dataInputStream, String title) throws IOException {
        TextListSection textListSection = new TextListSection(title);
        int listSize = dataInputStream.readInt();
        for (int i = 0; i < listSize; i++) {
            textListSection.addLine(dataInputStream.readUTF());
        }
        return textListSection;
    }

    private TimelineSection readTimelineSection(DataInputStream dataInputStream, String title) throws IOException {
        TimelineSection timelineSection = new TimelineSection(title);
        int listSize = dataInputStream.readInt();
        for (int i = 0; i < listSize; i++) {
            int timelinePeriodsSize = dataInputStream.readInt();
            TimelineSectionRecord timelineSectionRecord = new TimelineSectionRecord(dataInputStream.readUTF());
            for (int j = 0; j < timelinePeriodsSize; j++) {
                timelineSectionRecord.addPeriod(
                    YearMonth.parse(dataInputStream.readUTF()),
                    YearMonth.parse(dataInputStream.readUTF()),
                    dataInputStream.readUTF(),
                    dataInputStream.readUTF()
                );
            }
            timelineSection.add(timelineSectionRecord);
        }
        return timelineSection;
    }

    private void writeTimelineSection(DataOutputStream dataOutputStream, TimelineSection timelineSection) throws IOException {
        dataOutputStream.writeInt(timelineSection.getTimelineSectionRecords().size());
        for (TimelineSectionRecord timelineSectionRecord : timelineSection.getTimelineSectionRecords()) {
            dataOutputStream.writeInt(timelineSectionRecord.getPeriods().size());
            dataOutputStream.writeUTF(timelineSectionRecord.getName());
            for (TimelineSectionRecord.Period period : timelineSectionRecord.getPeriods()) {
                dataOutputStream.writeUTF(period.getDateBegin().toString());
                dataOutputStream.writeUTF(period.getDateEnd().toString());
                dataOutputStream.writeUTF(period.getDescription());
                dataOutputStream.writeUTF(period.getText() != null ? period.getText() : "");
            }
        }
    }

    private void writeTextListSection(DataOutputStream dataOutputStream, TextListSection textListSection) throws IOException {
        dataOutputStream.writeInt(textListSection.getTextLines().size());
        for (String line : textListSection.getTextLines()) {
            dataOutputStream.writeUTF(line);
        }
    }

    public void writeTextSection(DataOutputStream dataOutputStream, TextSection textSection) throws IOException {
        dataOutputStream.writeUTF(textSection.toString());
    }

    @Override
    public Resume readFromStorage(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int contactsSize = dataInputStream.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF());
            }

            int sectionsSize = dataInputStream.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                resume.addSection(sectionType, readSection(dataInputStream, sectionType));
            }
            return resume;
        }
    }
}