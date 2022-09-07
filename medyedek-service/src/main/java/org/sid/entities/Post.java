package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
	@JoinColumn(name="user",foreignKey = @ForeignKey(name = "FK_user_id"))
	public User user;


}
