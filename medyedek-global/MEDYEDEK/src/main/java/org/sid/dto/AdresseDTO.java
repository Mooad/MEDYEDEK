package org.sid.dto;

import org.sid.entities.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdresseDTO {
	
	private int adresse_id;
	private String Pays;
	private String Ville;
	private String Quartier;
	private String rue;
}
