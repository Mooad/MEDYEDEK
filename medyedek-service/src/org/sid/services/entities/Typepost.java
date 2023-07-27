package org.sid.services.entities;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="typepost")
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
