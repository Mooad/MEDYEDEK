package org.sid.entities;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name="donnateur") 
@DiscriminatorValue("DONN")
public class Donnateur extends Utilisateur {
	
	public Donnateur(String firstname, String lastname, String email, String cin, String phone_number,
			Domaine domaine, Adresse adresse, String confirmationmdp,String image) {
		super(firstname, lastname, email, cin, phone_number, domaine, adresse, confirmationmdp, image);
	
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -816675218965945926L;
	


	

}
