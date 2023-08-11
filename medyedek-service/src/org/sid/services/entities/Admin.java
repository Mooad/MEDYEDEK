package org.sid.services.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="admin")
public class Admin {
	@Id   @GeneratedValue
	private int id_admin;
	@JoinColumn(name="role_id", referencedColumnName="role_id")
	private  int admin_role;
}