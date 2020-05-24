package ru.javawebinar.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TimelineSection extends AbstractSection {
    private final List<TimelineSectionRecord> timelineSectionRecords = new ArrayList<>();

    private TimelineSection(){
        super();
    }
    public TimelineSection(String title) {
        super(title);
    }

    public void add(TimelineSectionRecord timelineSectionRecord){
        timelineSectionRecords.add(timelineSectionRecord);
    }

    public List<TimelineSectionRecord> getTimelineSectionRecords() {
        return timelineSectionRecords;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TimelineSectionRecord record: timelineSectionRecords) {
            sb.append(record.toString());
        }
        return sb.toString();
    }
}