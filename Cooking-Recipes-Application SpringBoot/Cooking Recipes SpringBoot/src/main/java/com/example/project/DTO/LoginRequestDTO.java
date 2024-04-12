package com.example.project.DTO;

import com.example.project.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank @NotEmpty @NotNull
    private String email;

    @NotBlank @NotEmpty @NotNull
    private String password;

}
