package org.sid.dto.profile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sid.dto.role.RoleDto;
import org.sid.entities.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileDto {
    public String image;
    public String firstname;
    public String lastname;
    public Address address;
    public String postalCode;
    public String phone_number;
    public String cin;
    public RoleDto role;
    public String email;
    public String  pseudo;
    public String domain;

}
