package com.koston.security;

import com.koston.users.User;
import com.koston.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository()
    {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = getUserRepository().findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found :("));

        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword(), List.of());
    }
}
