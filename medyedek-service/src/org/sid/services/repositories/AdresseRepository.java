 package org.sid.services.repositories;


 import org.sid.services.entities.Adress;
 import org.springframework.data.r2dbc.repository.R2dbcRepository;

 public interface AdresseRepository extends R2dbcRepository<Adress, Integer> {

}
