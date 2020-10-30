package org.sid.service.inscriptionservices;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.sid.dto.UtilisateurDto;
import org.sid.dto.mappers.AdresseMapper;
import org.sid.entities.Adresse;
import org.sid.entities.Beneficiaire;
import org.sid.entities.Domaine;
import org.sid.entities.Donnateur;
import org.sid.utilities.RoleConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sun.jersey.core.util.Base64;

import medyedek.service.mailconfirmation.RegistrationConfirmation;
import repositories.AdresseRepository;
import repositories.domaineRepository;
import repositories.utilisateurRepository;

@CrossOrigin
@RestController
@Service
public class UserService {

	@Autowired
	private utilisateurRepository utilisateurRepo;

	@Autowired
	private AdresseRepository adresseRepository;
	
	@Autowired
	private domaineRepository domaineRepo;
	

	@Autowired
	private RegistrationConfirmation mailConfirmation;

	public UserService() {

	}

	@RequestMapping(value = "/nouveauUtilisateur", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public void ajouterUtilisateur(@RequestBody String utilisateurData) {

		
		UtilisateurDto utilisateurDTO = new Gson().fromJson(utilisateurData, UtilisateurDto.class);

		AdresseMapper adresseMapper = new AdresseMapper();

		Adresse adresse = adresseMapper.mapAdresseFromUser(utilisateurDTO);
		List<Domaine> domaines = domaineRepo.findAll();
		Domaine chosen_domain=  null;
	for(Domaine domaine : domaines)
	
	{
		if(domaine.getNomdomaine().equals(utilisateurDTO.getDomaine()))
			utilisateurDTO.setDomaine_id(domaine.getDomaine_id());
	}
	domaines.clear();
	String imageDataBytes=null;
	
	if (utilisateurDTO.getImage() != null) {
		imageDataBytes = utilisateurDTO.getImage().substring(utilisateurDTO.getImage().indexOf(",") + 1);

	}

	utilisateurDTO.setImage(imageDataBytes);
	InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes()));
		adresseRepository.save(adresse);

		
		if (utilisateurDTO.getRole() == RoleConsts.DONNATEUR) {
			utilisateurRepo.save(new Donnateur(utilisateurDTO.getFirstname(), utilisateurDTO.getLastname(),
					utilisateurDTO.getEmail(), utilisateurDTO.getCin(), utilisateurDTO.getPhone_number(),
					chosen_domain, adresse, utilisateurDTO.getConfirmationmdp(),utilisateurDTO.getImage()));
		}
		if (utilisateurDTO.getRole() == RoleConsts.BENEFICIAIRE) {
			utilisateurRepo.save(new Beneficiaire(utilisateurDTO.getFirstname(), utilisateurDTO.getLastname(),
					utilisateurDTO.getEmail(), utilisateurDTO.getCin(), utilisateurDTO.getPhone_number(),
					chosen_domain, adresse, utilisateurDTO.getConfirmationmdp(),utilisateurDTO.getImage()));
		}
	
		mailConfirmation.sendMail("fajrimoad@gmail.com", "moad52@hotmail.fr",
		"HEHEHEH", "Registration to MEDYEDEK");

	}
}
