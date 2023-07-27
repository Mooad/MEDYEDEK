package org.sid.services.repositories;


import org.sid.services.entities.Domaine;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface DomaineRepository extends R2dbcRepository<Domaine, Integer> {

}
