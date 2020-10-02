package ru.catn.core.model;

import ru.catn.core.model.ids.TimeSheetId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "timesheets")
public class TimeSheet {
    @EmbeddedId
    private TimeSheetId id;

    @Column
    private Integer hours;

    @Column(name = "approved_hours")
    private Integer approvedHours;

    public TimeSheetId getId() {
        return id;
    }

    public void setId(TimeSheetId id) {
        this.id = id;
    }

    public Integer getApprovedHours() {
        return approvedHours;
    }

    public void setApprovedHours(Integer approvedHours) {
        this.approvedHours = approvedHours;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

}
