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
public class Address {

	@Id   @GeneratedValue(strategy=GenerationType.AUTO)
	private int adress_id;
	private String country;
	private String city;
	private String district;
	private String street;
	private String postalCode;

	public int getAdress_id() {
		return adress_id;
	}

	@OneToOne
	private User utilisteur;
	
	
}
