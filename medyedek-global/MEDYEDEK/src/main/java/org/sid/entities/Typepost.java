package org.sid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Transient
public class Typepost {

	
@Id    @GeneratedValue(strategy=GenerationType.AUTO)
private int id_typepost;
private String nomtypepost ;
@OneToMany
private List<Post> post;

public Typepost(String nomtypepost) {
	super();
	this.nomtypepost = nomtypepost;
}



}
