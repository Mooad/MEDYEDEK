package  org.sid.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Entity
@DiscriminatorValue("BENEF")
public class Beneficiaire extends Utilisateur {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Beneficiaire(String firstname, String lastname, String email, String cin, String phone_number,
			Domaine domaine, Adresse adresse, String confirmationmdp ,String image) {
		super(firstname, lastname, email, cin, phone_number, domaine, adresse, confirmationmdp,image);

	}

	public int nombre_postes;
	
}
