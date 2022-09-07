package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_UTILISATEUR", discriminatorType = DiscriminatorType.STRING, length = 10)
public abstract class User implements Serializable {
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
    @Column(name = "image")
    protected byte[] image;
    @Column(name = "is_valid_user")
    protected String isValidUser;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "FK_role_utilisateur"))
    protected Role role;
    @ManyToOne(targetEntity = Domaine.class)
    @JoinColumn(name = "domaine_id", foreignKey = @ForeignKey(name = "FK_utilisateur_domaine"))
    protected Domaine domaine;
    @OneToOne(targetEntity = Address.class)
    @JoinColumn(name = "adresse_id", foreignKey = @ForeignKey(name = "FK_adresse_utilisateur"))
    protected Address address;
    protected String password;
    protected String userToken;
    protected String temp_pass;


    public User(String firstname, String lastname,String pseudo, String email, String cin, String phone_number, Domaine domaine, Address address, String password, String image,Role role,String token) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pseudo=pseudo;
        this.email = email;
        this.cin = cin;
        this.phone_number = phone_number;
        this.domaine = domaine;
        this.address = address;
        this.password = password;
        this.image = image==null ? null : Base64.getEncoder().encode(image.getBytes());
        this.userToken = token;
        this.role=role;
    }


    public boolean isNotValid() {
        if (isValidUser ==null || !this.isValidUser.equals("01")) {
            return true;
        }
        return false;

    }


}
