package ru.catn.core.model;

import java.util.List;

public class TimeSheetsToApprove {
    private List<TimeSheet> timeSheets;

    public TimeSheetsToApprove(List<TimeSheet> timeSheets) {
        this.timeSheets = timeSheets;
    }

    public List<TimeSheet> getTimeSheets() {
        return timeSheets;
    }

    public void setTimeSheets(List<TimeSheet> timeSheets) {
        this.timeSheets = timeSheets;
    }
}
