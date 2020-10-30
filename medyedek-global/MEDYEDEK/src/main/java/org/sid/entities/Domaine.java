package org.sid.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
