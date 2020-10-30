package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import  org.sid.entities.Utilisateur;



@CrossOrigin
@Repository
public interface utilisateurRepository  extends JpaRepository<Utilisateur, Integer>{

}
