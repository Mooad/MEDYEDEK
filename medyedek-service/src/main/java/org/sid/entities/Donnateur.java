package org.sid.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name="donnateur") 
@DiscriminatorValue("DONN")
public class Donnateur extends Utilisateur {
	
	public Donnateur(String firstname, String lastname, String email, String cin, String phone_number,
			Domaine domaine, Adresse adresse, String confirmationmdp,String image,Role role,String token) {
		super(firstname, lastname, email, cin, phone_number, domaine, adresse, confirmationmdp, image,role,token);
	
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -816675218965945926L;
	


	

}
