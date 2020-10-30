package org.sid.dto.mappers;

import java.lang.reflect.Field;

import org.sid.dto.AdresseDTO;
import org.sid.dto.UtilisateurDto;
import org.sid.entities.Adresse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class AdresseMapper {

	public AdresseMapper()
	{}
	
	public Adresse mapAdresseFromUser(UtilisateurDto utilisateurDto)
	{
		Adresse  adresse = new Adresse();
		adresse.setPays(utilisateurDto.getPays());
		adresse.setVille(utilisateurDto.getVille());
		adresse.setQuartier(utilisateurDto.getQuartier());
		adresse.setRue(utilisateurDto.getRue());
		return adresse;
	}
	
	@Override
	public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append(getClass().getName());
	  sb.append(": ");
	  for (Field f : getClass().getDeclaredFields()) {
	    sb.append(f.getName());
	    sb.append("=");
	    try {
			sb.append(f.get(this));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    sb.append(", ");
	  }
	  return sb.toString();
	}
}
