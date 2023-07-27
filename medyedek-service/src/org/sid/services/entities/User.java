package org.sid.services.entities;

import lombok.*;
import jakarta.persistence.*;
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
public  class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    public int user_id;
    public String firstname;
    public String lastname;
    public String pseudo;
    public String email;
    public String cin;
    public String phone_number;
    @Column(name = "image")
    public String image;
    @Column(name = "is_valid_user")
    public String isValidUser;

    @ManyToOne(targetEntity = Role.class,fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role")
    public Role role;
    @ManyToOne(targetEntity = Domaine.class,fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "domaine_id")
    public Domaine domaine;
    @OneToOne(targetEntity = Adress.class,fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id")
    public Adress address;
    public String password;

    public String userToken;
    public String temp_pass;


    public User(String firstname, String lastname,String pseudo, String email, String cin, String phone_number, Domaine domaine, Adress address, String password, String image,Role role,String token) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pseudo=pseudo;
        this.email = email;
        this.cin = cin;
        this.phone_number = phone_number;
        this.domaine = domaine;
        this.address = address;
        this.password = password;
        this.image = image;
        this.userToken = token;
        this.role=role;
    }


    public boolean isValid() {
		return isValidUser == null || !this.isValidUser.equals("01");

	}


}
