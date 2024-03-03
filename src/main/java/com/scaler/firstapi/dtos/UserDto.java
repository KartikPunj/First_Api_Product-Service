package com.scaler.firstapi.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    @JsonIgnore
    private List<Role> roles;
    private boolean isEmailVerified;

}
