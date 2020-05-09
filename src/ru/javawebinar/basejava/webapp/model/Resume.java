package ru.javawebinar.basejava.webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    // Person full name
    private String fullName;

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

//    @Override
//    public String toString() {
//        return uuid;
//    }

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
        return Objects.equals(uuid, resume.uuid);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Resume resume = (Resume) o;
//        return uuid.equals(resume.uuid) &&
//                fullName.equals(resume.fullName);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume resume) {
//        return Integer.compare(this.hashCode(), resume.hashCode());
        return uuid.compareTo(resume.uuid);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
