package org.sid.services.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Entity
@DiscriminatorValue("BENEF")
public class Beneficiaire extends User {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Beneficiaire(String firstname, String lastname,String pseudo, String email, String cin, String phone_number,
			Domaine domaine, Adress adress, String confirmationmdp ,String image,Role role,String token) {
		super(firstname, lastname, pseudo,email, cin, phone_number, domaine, adress, confirmationmdp,image, role,token);

	}

	public int nombre_postes;
	
}
