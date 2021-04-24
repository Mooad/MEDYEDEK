package org.sid.repositories;

import org.sid.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Integer> {

}
