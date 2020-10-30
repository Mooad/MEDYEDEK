package org.sid.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_UTILISATEUR",discriminatorType=DiscriminatorType.STRING,length=10)
public abstract class Utilisateur  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	protected int user_id;
	protected String 	firstname;
	protected String 	lastname;
	protected String 	email;
	protected String 	cin;
	protected String phone_number;
	@Column(name="image")
	protected byte[] image;
	protected String isValidUser ;

	@ManyToOne(targetEntity =Role.class)
	@JoinColumn(name = "id_role" ,foreignKey = @ForeignKey(name = "FK_role_utilisateur"))
	protected Role role;
	@ManyToOne(targetEntity =Domaine.class)
	@JoinColumn(name = "domaine_id" ,foreignKey = @ForeignKey(name = "FK_utilisateur_domaine"))
	protected Domaine domaine;
	@OneToOne(targetEntity =Adresse.class)
	@JoinColumn(name = "adresse_id" ,foreignKey = @ForeignKey(name = "FK_adresse_utilisateur"))
	protected Adresse adresse;
	protected String 	password;
	public Utilisateur( String firstname,String lastname, String email , String cin,String phone_number,Domaine domaine, Adresse adresse , String password,String image)
	{
		this.firstname =firstname;
		this.lastname =lastname;
		this.email =email;
		this.cin =cin;
		this.phone_number=phone_number;
		this.domaine=domaine;
		this.adresse=adresse;
		this.password=password;
		this.image = Base64.getEncoder().encode(image.getBytes());
		
	}
	

}
