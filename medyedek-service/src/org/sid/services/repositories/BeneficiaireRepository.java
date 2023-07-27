package org.sid.services.repositories;


import org.sid.services.entities.Beneficiaire;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BeneficiaireRepository extends R2dbcRepository<Beneficiaire, Integer> {

}
