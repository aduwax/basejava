package ru.javawebinar.basejava.webapp.model;

import ru.javawebinar.basejava.util.YearMonthAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class TimelineSectionRecord implements Serializable {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth dateBegin;
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth dateEnd;
        private String description;
        private String text;

        private Period(){}

        Period(YearMonth dateBegin, YearMonth dateEnd, String description, String text){
            this.dateBegin = dateBegin;
            this.dateEnd = dateEnd;
            this.description = description;
            this.text = text;
        }

        Period(YearMonth dateBegin, YearMonth dateEnd, String description){
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

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return dateBegin + " - " + dateEnd + " " + description + (text != null ? "\n" + text : "");
        }
    }
    private String name;
    private final List<Period> periods = new ArrayList<>();

    private TimelineSectionRecord() {
        super();
    }
    public TimelineSectionRecord(String name){
        this.name = name;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public TimelineSectionRecord addPeriod(YearMonth dateBegin, YearMonth dateEnd, String description, String text){
        periods.add(new Period(dateBegin, dateEnd, description, text));
        return this;
    }

    public TimelineSectionRecord addPeriod(YearMonth dateBegin, YearMonth dateEnd, String description){
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
