package repositories;

import org.sid.entities.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Integer> {

}
