package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Entity
public class Adresse {

	@Id   @GeneratedValue(strategy=GenerationType.AUTO)
	private int adresse_id;
	private String Pays;
	private String Ville;
	private String Quartier;
	private String rue;
	public int getAdresse_id() {
		return adresse_id;
	}
	public void setAdresse_id(int adresse_id) {
		this.adresse_id = adresse_id;
	}
	public String getPays() {
		return Pays;
	}
	public void setPays(String pays) {
		Pays = pays;
	}
	public String getVille() {
		return Ville;
	}
	public void setVille(String ville) {
		Ville = ville;
	}
	public String getQuartier() {
		return Quartier;
	}
	public void setQuartier(String quartier) {
		Quartier = quartier;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public Utilisateur getUtilisteur() {
		return utilisteur;
	}
	public void setUtilisteur(Utilisateur utilisteur) {
		this.utilisteur = utilisteur;
	}
	@OneToOne
	private Utilisateur utilisteur;
	
	
}
