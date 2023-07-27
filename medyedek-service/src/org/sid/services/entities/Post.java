package org.sid.services.entities;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	public int post_id;
	@ManyToOne(targetEntity =Typepost.class )
	@JoinColumn(name="id_typepost",foreignKey = @ForeignKey(name = "FK_post_type_post"))
	public Typepost typepost;
	public String textContent;
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER ,cascade = CascadeType.ALL,mappedBy = "user")
	public User user;
	@OneToMany
	public List<Content> content;
	@Column(name="likesNB")
	public Integer likesNB;
	@Column(name="commentsNB")
	public Integer commentsNB;
	@Column(name="sharesNB")
	public Integer sharesNB;
	@Column(name="commentContent")
	public String commentContent;

}
