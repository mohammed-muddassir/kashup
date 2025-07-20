package com.koston.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class CustomUserDetails implements UserDetails
{
    private final Long id;
    private final String email;
    private final String password;
    private final List authorities;

    public CustomUserDetails(Long id, String email, String password,
            List authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public Long getUserId() {
        return id;
    }

    @Override
    public List getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
