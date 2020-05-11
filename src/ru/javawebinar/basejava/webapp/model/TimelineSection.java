package ru.javawebinar.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TimelineSection extends AbstractSection {
    private final List<TimelineSectionRecord> timelineSectionRecords = new ArrayList<>();

    TimelineSection(String title) {
        super(title);
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