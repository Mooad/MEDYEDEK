package org.sid.metier;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import org.sid.entities.Donnateur;
import org.sid.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;
import repositories.utilisateurRepository;



@Getter
@Setter
@Path("json/inscription")
public class InscriptionBS {

	@Autowired 
	private utilisateurRepository UtilisateurRepository;
	
	@Path("json/utilisateur")
	public void Inscrire(Utilisateur utilisateur)
	{
    if(utilisateur!=null)
    {
    	UtilisateurRepository.save(utilisateur);
    }

	}
	
}
