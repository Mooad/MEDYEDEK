package org.sid.dto;


import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class UtilisateurDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_id;
	private String 	firstname;
	private String 	lastname;
	private String age;
	private String 	email;
	private String 	cin;
	private String  postalcode;
	private String  image;
	private String phone_number;
	private String confirmationmdp;
	private String domaine;
	private String country;
	private int role;
	private int domaine_id;
	private String pays;
	private String ville;
	private String quartier;
	private String rue;
	


	
	public  UtilisateurDto()
	{
		
	}
	
	
}
