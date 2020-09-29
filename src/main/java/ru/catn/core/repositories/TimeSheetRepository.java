package ru.catn.core.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.catn.core.model.TimeSheet;
import ru.catn.core.model.ids.TimeSheetId;
import ru.catn.core.reportModels.UserApprovedHours;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeSheetRepository extends CrudRepository<TimeSheet, TimeSheetId> {

    List<TimeSheet> findByIdUserIdAndIdDate(Integer userId, LocalDate date);

    List<TimeSheet> findByApprovedHours(Integer approvedHours);

    @Query(value = "SELECT u.id as id, u.firstname as firstName, u.lastname as lastName, sum(t.approved_hours) as approvedHours FROM timesheets as t " +
            "inner join users as u " +
            "on u.id = t.user_id " +
            "where t.task_id = ? " +
            "group by u.id, u.firstname, u.lastname", nativeQuery = true)
    List<UserApprovedHours> findUserApprovedHoursByTaskAsArray(Integer taskId);
}
