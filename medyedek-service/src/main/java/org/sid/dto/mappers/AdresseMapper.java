package org.sid.dto.mappers;


import org.sid.dto.profile.ProfileDto;
import org.sid.dto.user.UtilisateurDto;
import org.sid.entities.Address;
import org.sid.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Component
@Service
public class AdresseMapper {

	public AdresseMapper()
	{}
	
	public Address mapAdresseFromUser(UtilisateurDto utilisateurDto)
	{
		Address  adress = new Address();
		adress.setCountry(utilisateurDto.getPays());
		adress.setCity(utilisateurDto.getVille());
		adress.setDistrict(utilisateurDto.getQuartier());
		adress.setStreet(utilisateurDto.getRue());
		adress.setPostalCode(utilisateurDto.getPostalcode());
		return adress;
	}

	public void mapAdresseFromProfileDto(ProfileDto profileDto,User user )
	{
		user.getAddress().setCountry(profileDto.getAddress().getCountry());
		user.getAddress().setCity(profileDto.getAddress().getCity());
		user.getAddress().setDistrict(profileDto.getAddress().getDistrict());
		user.getAddress().setStreet(profileDto.getAddress().getCity());
		user.getAddress().setPostalCode(profileDto.getPostalCode());

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
