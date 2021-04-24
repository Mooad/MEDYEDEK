package org.sid.dto.validators;

import org.sid.dto.UtilisateurDto;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

	public UserValidator() {
	}

	public boolean validerDonneesUtilisateur(UtilisateurDto utilisateurDto) {
		if (utilisateurDto.getCin() != null && utilisateurDto.getFirstname() != null
				&& utilisateurDto.getLastname() != null)
			return true;
		else
			return false;
	}
}
