package com.managementkasmasjid.security;

import com.managementkasmasjid.entity.User;
import com.managementkasmasjid.repository.UserRepository;
import com.managementkasmasjid.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserPrincipalService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(s);
        System.out.println("byUsername = " + byUsername);
        if (byUsername == null){
            throw new UsernameNotFoundException(s);
        }
        return new UserPrincipal(byUsername);
    }
}
