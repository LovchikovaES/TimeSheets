package ru.catn.core;

import org.springframework.format.annotation.DateTimeFormat;
import ru.catn.core.reportModels.UserApprovedHours;

import java.time.LocalDate;
import java.util.List;

public class ReportTask {
    private Integer taskId;
    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    private List<UserApprovedHours> userApprovedHours;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public List<UserApprovedHours> getUserApprovedHours() {
        return userApprovedHours;
    }

    public void setUserApprovedHours(List<UserApprovedHours> userApprovedHours) {
        this.userApprovedHours = userApprovedHours;
    }
}
