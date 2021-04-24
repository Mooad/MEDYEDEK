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
public abstract class Utilisateur implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    protected int user_id;
    protected String firstname;
    protected String lastname;
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
    @OneToOne(targetEntity = Adresse.class)
    @JoinColumn(name = "adresse_id", foreignKey = @ForeignKey(name = "FK_adresse_utilisateur"))
    protected Adresse adresse;
    protected String password;
    protected String userToken;



    public Utilisateur(String firstname, String lastname, String email, String cin, String phone_number, Domaine domaine, Adresse adresse, String password, String image,String token) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.cin = cin;
        this.phone_number = phone_number;
        this.domaine = domaine;
        this.adresse = adresse;
        this.password = password;
        this.image = image==null ? null : Base64.getEncoder().encode(image.getBytes());
        this.userToken = token;

    }


    public boolean isNotValid() {
        if (isValidUser ==null || !this.isValidUser.equals("01")) {
            return true;
        }
        return false;

    }


}
