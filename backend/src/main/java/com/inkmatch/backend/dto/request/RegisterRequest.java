package com.inkmatch.backend.dto.request;
import com.inkmatch.backend.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String fullName;
    private String email;
    private Role role;
    private String password;

}