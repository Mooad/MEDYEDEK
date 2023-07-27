package org.sid.services.dto.validators;


import org.sid.services.dto.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

	public UserValidator() {
	}

	public boolean validerDonneesUtilisateur(UserDto userDto) {
		return userDto.getCin() != null && userDto.getFirstname() != null
				&& userDto.getLastname() != null;
	}
}
