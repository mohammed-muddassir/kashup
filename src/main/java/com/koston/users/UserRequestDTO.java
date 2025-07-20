package com.koston.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotNull @NotEmpty String firstName,
        String lastName,
        @Email String email,
        @NotNull @NotEmpty String password) {
}
