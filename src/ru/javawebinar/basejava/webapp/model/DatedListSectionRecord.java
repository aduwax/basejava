package ru.javawebinar.basejava.webapp.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DatedListSectionRecord implements Serializable {

    private static class Period implements Serializable {
        private final YearMonth dateBegin;
        private final YearMonth dateEnd;
        private final String description;
        private final String represent;

        Period(YearMonth dateBegin, YearMonth dateEnd, String description, String represent) {
            this.dateBegin = dateBegin;
            this.dateEnd = dateEnd;
            this.description = description;
            this.represent = represent;
        }

        Period(YearMonth dateBegin, YearMonth dateEnd, String description) {
            this(dateBegin, dateEnd, description, null);
        }

        public YearMonth getDateBegin() {
            return dateBegin;
        }

        public YearMonth getDateEnd() {
            return dateEnd;
        }

        public String getDescription() {
            return description;
        }

        public String getRepresent() {
            return represent;
        }

        @Override
        public String toString() {
            return dateBegin + " - " + dateEnd + " " + description + (represent != null ? "\n" + represent : "");
        }
    }

    private final String name;
    private final List<Period> periods = new ArrayList<>();

    public DatedListSectionRecord(String name) {
        this.name = name;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public DatedListSectionRecord addPeriod(YearMonth dateBegin, YearMonth dateEnd, String description, String represent) {
        periods.add(new Period(dateBegin, dateEnd, description, represent));
        return this;
    }

    public DatedListSectionRecord addPeriod(YearMonth dateBegin, YearMonth dateEnd, String description) {
        periods.add(new Period(dateBegin, dateEnd, description));
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(name + "\n");
        for (Period period : periods) {
            sb.append(period.toString()).append("\n");
        }
        return sb.toString();
    }
}