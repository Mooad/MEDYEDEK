package org.sid.dto.adresse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdresseDTO {
	private int adress_id;
	private String country;
	private String city;
	private String district;
	private String street;
	private String postalCode;
}
