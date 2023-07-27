package org.sid.services.nosql.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    @Id
    private String id;
    private String text;
    private String content;
    private String parent;


}