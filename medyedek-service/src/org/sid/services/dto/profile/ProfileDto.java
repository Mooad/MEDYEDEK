package org.sid.services.dto.profile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.sid.services.dto.role.RoleDto;
import org.sid.services.entities.Adress;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileDto {
    public String image;
    public String firstname;
    public String lastname;
    public Adress address;
    public String postalCode;
    public String phone_number;
    public String cin;
    public RoleDto role;
    public String email;
    public String  pseudo;
    public String domain;

}
