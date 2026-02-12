package org.example.turismoapp.security;

import lombok.RequiredArgsConstructor;
import org.example.turismoapp.model.UserEntity;
import org.example.turismoapp.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> currentUser = userRepository.findByUsername(username);

        if (currentUser.isEmpty()) {
            throw new UsernameNotFoundException("Username not found : " + username);
        }


        return User.withUsername(username)
                .password("{noop}" + currentUser.get().getPassword())
                .roles(currentUser.get().getRole())
                .build();
    }
}