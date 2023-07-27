package org.sid.services.entities;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="role")
@Builder
public class Role {
	@Id   @GeneratedValue
	public int role_id;
	public String rolename;
	@OneToMany
	private List<User> utilisateurs;
}
