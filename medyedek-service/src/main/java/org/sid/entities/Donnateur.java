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
@DiscriminatorValue("DONN")
public class Donnateur extends User {

	public Donnateur(String firstname, String lastname,String pseudo, String email, String cin, String phone_number,
			Domaine domaine, Address adress, String confirmationmdp,String image,Role role,String token) {
		super(firstname, lastname,pseudo, email, cin, phone_number, domaine, adress, confirmationmdp, image,role,token);

	}


	/**
	 *
	 */
	private static final long serialVersionUID = -816675218965945926L;





}
