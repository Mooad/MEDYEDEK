package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Transient;

import javax.persistence.*;
import java.util.List;

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
