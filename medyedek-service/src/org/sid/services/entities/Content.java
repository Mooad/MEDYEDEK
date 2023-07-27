package org.sid.services.entities;

import lombok.*;

import jakarta.persistence.*;
import org.sid.services.enumeration.ContentType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private ContentType contentType ;
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "content_post_fk"))
    private int post_id;


}
