package ru.javawebinar.basejava.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    // Person full name
    private String fullName;
    private Map<String, String> contacts = new HashMap<>();
    private final Map<SectionType, ResumeSection> sections = new HashMap<>();

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this(uuid, "Unknown person");
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume resume) {
        int fullNameCompareResult = fullName.compareTo(resume.fullName);
        return  fullNameCompareResult == 0
                ? uuid.compareTo(resume.uuid)
                : fullNameCompareResult;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<String, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }

    public void addContact(String contactName, String contactData) {
        contacts.put(contactName, contactData);
    }

    public void addSection(SectionType sectionType, ResumeSection section) {
        sections.put(sectionType, section);
    }

    public ResumeSection getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public Map<SectionType, ResumeSection> getSections() {
        return sections;
    }

    interface ResumeSection {
        void write();
        String getTitle();
    }

    private abstract static class AbstractResumeSection implements ResumeSection {
        private final String title;

        AbstractResumeSection(String title) {
            this.title = title;
        }

        @Override
        public String getTitle(){
            return title;
        }
    }

    public static class TextSection extends AbstractResumeSection {
        private String text;

        TextSection(String title) {
            super(title);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public void write() {
            System.out.println(text);
        }
    }

    public static class TextListSection extends AbstractResumeSection {
        List<String> textLines = new ArrayList<>();
        TextListSection(String title) {
            super(title);
        }

        public List<String> getTextLines() {
            return textLines;
        }

        public void addLine(String line){
            textLines.add(line);
        }

        @Override
        public void write() {
            for (String line: textLines) {
                System.out.println(line);
            }
        }
    }

    public static class TimelineSection extends AbstractResumeSection {
        private final List<TimelineSectionRecord> timelineSectionRecords = new ArrayList<>();

        TimelineSection(String title) {
            super(title);
        }

        public static class TimelineSectionRecord {
            private final String dateBegin;
            private final String dateEnd;
            private final String name;
            private final String description;
            private final String text;
            public TimelineSectionRecord(
                    String dateBegin,
                    String dateEnd,
                    String name,
                    String description,
                    String text
            ){
                this.dateBegin = dateBegin;
                this.dateEnd = dateEnd;
                this.name = name;
                this.description = description;
                this.text = text;
            }

            public TimelineSectionRecord(
                    String dateBegin,
                    String dateEnd,
                    String name,
                    String description
            ){
                this(dateBegin, dateEnd, name, description, null);
            }

            public String getDateBegin() {
                return dateBegin;
            }

            public String getDateEnd() {
                return dateEnd;
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public String getText() {
                return text;
            }

            public String toString() {
                return name + "\n" +
                        dateBegin + " - " + dateEnd + " " + description + "\n" +
                        text;
            }
        }

        public void add(TimelineSectionRecord timelineSectionRecord){
            timelineSectionRecords.add(timelineSectionRecord);
        }

        @Override
        public void write() {
            for (TimelineSectionRecord record: timelineSectionRecords) {
                System.out.println(record.toString());
            }
        }
    }
}
