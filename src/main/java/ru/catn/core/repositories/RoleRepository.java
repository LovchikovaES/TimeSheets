package ru.catn.core.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.catn.core.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
