package org.sid.entities;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="post") 
public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id   @GeneratedValue(strategy=GenerationType.AUTO)
	public int id_post;
	@ManyToOne(targetEntity =Typepost.class )
	@JoinColumn(name="id_typepost",foreignKey = @ForeignKey(name = "FK_post_type_post"))
	public Typepost typepost;
	public String textContent;
	@OneToOne
	@JoinColumn(name="beneficiaire_id",foreignKey = @ForeignKey(name = "FK_beneficiaire_id"))
	public Beneficiaire Beneficiaire;
	public byte[] postdata;
	
}
