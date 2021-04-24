package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "domaine")
public class Domaine implements Serializable {

	public Domaine() {
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 8180740934545387391L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer domaine_id;

	private String nomdomaine;


}
