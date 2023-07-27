package org.sid.services.emaildto;


/*
 @Author Moad Fajri
 @Date 11/06/20, THur
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private String receiverName;
    private String receiverEmail;
    private String subject;
    private String message;
}