package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="role")
public class Role {
	@Id   @GeneratedValue
	private int role_id;
	private String rolename;
	@OneToMany
	private List<User> utilisateurs;
}
