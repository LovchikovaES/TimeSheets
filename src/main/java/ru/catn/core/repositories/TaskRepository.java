package ru.catn.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.catn.core.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
