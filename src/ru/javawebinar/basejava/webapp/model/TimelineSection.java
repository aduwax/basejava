package ru.javawebinar.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TimelineSection extends AbstractSection {
    private final List<TimelineSectionRecord> timelineSectionRecords = new ArrayList<>();

    public TimelineSection(String title) {
        super(title);
    }

    public void add(TimelineSectionRecord timelineSectionRecord) {
        timelineSectionRecords.add(timelineSectionRecord);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TimelineSectionRecord record : timelineSectionRecords) {
            sb.append(record.toString());
        }
        return sb.toString();
    }
}