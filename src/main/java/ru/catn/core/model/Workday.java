package ru.catn.core.model;

import org.springframework.format.annotation.DateTimeFormat;
import ru.catn.core.model.ids.TimeSheetId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Workday {
    private List<TimeSheet> timeSheets = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private User user;

    public Workday() {
    }

    public Workday(List<TimeSheet> timeSheets, LocalDate date, User user) {
        this.timeSheets = timeSheets;
        this.date = date;
        this.user = user;
    }

    public List<TimeSheet> getTimeSheets() {
        return timeSheets;
    }

    public void setTimeSheets(List<TimeSheet> timeSheets) {
        this.timeSheets = timeSheets;
    }

    public void addTimeSheet(TimeSheet timeSheet) {
        TimeSheetId timeSheetId = new TimeSheetId();
        timeSheetId.setUser(this.user);
        timeSheetId.setDate(this.date);
        timeSheet.setId(timeSheetId);
        this.getTimeSheets().add(timeSheet);
    }

    public void removeTimeSheet(int index) {
        timeSheets.remove(index);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
