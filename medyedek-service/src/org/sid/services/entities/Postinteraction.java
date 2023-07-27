package org.sid.services.entities;

import lombok.*;

import jakarta.persistence.*;
import org.sid.services.enumeration.InteractionType;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="postinteraction")
public class Postinteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "postinteraction_post_fk"))
    private int post_id;
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "postinteraction_user_fk"))
    private int user_id;
    @Enumerated(EnumType.STRING)
    @Column(name = "interaction_type")
    public InteractionType interaction_type;
    public String comment;

}