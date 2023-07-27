package org.sid.services.repositories;


import org.sid.services.entities.Donnateur;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
public interface DonnateurRepository extends R2dbcRepository<Donnateur, Integer> {

	

}
