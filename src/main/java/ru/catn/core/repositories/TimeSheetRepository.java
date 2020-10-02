package ru.catn.core.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.catn.core.model.TimeSheet;
import ru.catn.core.model.ids.TimeSheetId;

import java.time.LocalDate;
import java.util.List;

public interface TimeSheetRepository extends CrudRepository<TimeSheet, TimeSheetId> {

    List<TimeSheet> findByIdUserIdAndIdDate(Integer userId, LocalDate date);

    List<TimeSheet> findByApprovedHours(Integer approvedHours);

}
