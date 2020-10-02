package ru.catn.core.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.catn.core.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
