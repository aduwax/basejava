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
    private Map<String, String> contacts;
    private Map<SectionType, ResumeSection> sections;

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

        }
    }

    private static class TimelineSection extends AbstractResumeSection {
        TimelineSection(String title) {
            super(title);
        }

        private class TimelineSectionRecord {
            private final Date dateBegin;
            private final Date dateEnd;
            private final String name;
            private final String description;
            private final String text;
            TimelineSectionRecord(
                    Date dateBegin,
                    Date dateEnd,
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

            private List<TimelineSectionRecord> timelineSectionRecords;

            TimelineSectionRecord(
                    Date dateBegin,
                    Date dateEnd,
                    String name,
                    String description
            ){
                this(dateBegin, dateEnd, name, description, null);
            }
        }

        @Override
        public void write() {

        }
    }
}
