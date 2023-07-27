package org.sid.services.entities;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name="address")
public class Adress {

	@Id   @GeneratedValue(strategy=GenerationType.AUTO)
	private int adress_id;
	private String country;
	private String city;
	private String district;
	private String street;
	private String postalCode;


	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User utilisteur;
	
	
}
