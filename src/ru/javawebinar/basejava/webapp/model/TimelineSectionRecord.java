package ru.javawebinar.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TimelineSectionRecord {

    private static class Period {
        private final String dateBegin;
        private final String dateEnd;
        private final String description;
        private final String text;

        Period(String dateBegin, String dateEnd, String description, String text){
            this.dateBegin = dateBegin;
            this.dateEnd = dateEnd;
            this.description = description;
            this.text = text;
        }

        Period(String dateBegin, String dateEnd, String description){
            this(dateBegin, dateEnd, description, null);
        }

        public String getDateBegin() {
            return dateBegin;
        }

        public String getDateEnd() {
            return dateEnd;
        }

        public String getDescription() {
            return description;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return dateBegin + " - " + dateEnd + " " + description + (text != null ? "\n" + text : "");
        }
    }
    private final String name;
    private final List<Period> periods = new ArrayList<>();

    public TimelineSectionRecord(String name){
        this.name = name;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public TimelineSectionRecord addPeriod(String dateBegin, String dateEnd, String description, String text){
        periods.add(new Period(dateBegin, dateEnd, description, text));
        return this;
    }

    public TimelineSectionRecord addPeriod(String dateBegin, String dateEnd, String description){
        periods.add(new Period(dateBegin, dateEnd, description));
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(name + "\n");
        for (Period period:periods) {
            sb.append(period.toString()).append("\n");
        }
        return sb.toString();
    }
}
