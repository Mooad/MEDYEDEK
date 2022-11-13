package org.sid.dto.validators;

import org.sid.dto.user.UtilisateurDto;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

	public UserValidator() {
	}

	public boolean validerDonneesUtilisateur(UtilisateurDto utilisateurDto) {
		return utilisateurDto.getCin() != null && utilisateurDto.getFirstname() != null
				&& utilisateurDto.getLastname() != null;
	}
}
