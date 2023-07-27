package org.sid.services.entities.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.sid.services.entities.Adress;
import org.sid.services.entities.Domaine;
import org.sid.services.entities.Role;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_UTILISATEUR", discriminatorType = DiscriminatorType.STRING, length = 10)
@Table(name = "user")
public  class ReactiveUserEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    protected int user_id;
    protected String firstname;
    protected String lastname;
    protected String pseudo;
    protected String email;
    protected String cin;
    protected String phone_number;
    protected String image;
    protected String isValidUser;
    protected String id_role;
    protected String domaine_id;
    protected String adresse_id;
    protected String password;

    protected String userToken;
    protected String temp_pass;


    public ReactiveUserEntity(String firstname, String lastname, String pseudo, String email, String cin, String phone_number, String domaine, String address, String password, String image, String id_role, String token) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pseudo=pseudo;
        this.email = email;
        this.cin = cin;
        this.phone_number = phone_number;
        this.domaine_id = domaine;
        this.adresse_id = address;
        this.password = password;
        this.image = image;
        this.userToken = token;
        this.id_role=id_role;
    }


    public boolean isValid() {
		return isValidUser == null || !this.isValidUser.equals("01");

	}


}
