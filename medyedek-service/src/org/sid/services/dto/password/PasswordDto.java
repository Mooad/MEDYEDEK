package org.sid.services.dto.password;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordDto implements Serializable {
    public String email;
    public String tempPass;
    public String newPass;
}
