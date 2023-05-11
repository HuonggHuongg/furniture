package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.Users;
import com.graduation.furniture.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users userEntity = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username : " + username));

        Collection<? extends GrantedAuthority> grantedAuthorities = userEntity.getUserRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole().getRoleName()))
                .collect(Collectors.toList());

        return new User(userEntity.getUserName(), userEntity.getPassword(), true, true,
                true, true, grantedAuthorities);
    }
}
