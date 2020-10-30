package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.sid.entities.Typepost;
import  org.sid.entities.Utilisateur;


@CrossOrigin
public interface TypepostRepository  extends JpaRepository<Typepost, Integer>{

}
