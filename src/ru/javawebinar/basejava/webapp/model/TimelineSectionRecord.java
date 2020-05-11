package ru.javawebinar.basejava.webapp.model;

public class TimelineSectionRecord {
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
