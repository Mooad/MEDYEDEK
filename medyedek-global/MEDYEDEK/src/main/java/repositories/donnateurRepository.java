package repositories;

import org.sid.entities.Donnateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
public interface donnateurRepository extends JpaRepository<Donnateur, Integer>{

	

}
