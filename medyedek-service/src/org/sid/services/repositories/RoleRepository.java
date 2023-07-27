package org.sid.services.repositories;


import org.sid.services.entities.Role;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RoleRepository extends R2dbcRepository<Role, Integer> {



}

