package org.sid.repositories;


import org.sid.entities.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface DomaineRepository extends JpaRepository<Domaine, Integer> {

}
