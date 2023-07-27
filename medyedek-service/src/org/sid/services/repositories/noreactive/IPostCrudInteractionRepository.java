package org.sid.services.repositories.noreactive;


import org.sid.services.entities.Postinteraction;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IPostCrudInteractionRepository extends R2dbcRepository<Postinteraction, Integer> {


}