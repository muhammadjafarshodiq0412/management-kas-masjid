package com.managementkasmasjid.service.impl;

import com.managementkasmasjid.dto.request.UserRequestDto;
import com.managementkasmasjid.entity.Role;
import com.managementkasmasjid.entity.User;
import com.managementkasmasjid.repository.RoleRepository;
import com.managementkasmasjid.repository.UserRepository;
import com.managementkasmasjid.repository.UserRepository;
import com.managementkasmasjid.service.UserService;
import com.managementkasmasjid.service.UserService;
import com.managementkasmasjid.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CommonUtil util;

    @Override
    public User save(User data) {
        log.info("User Save");
        User user = new User();
        Role role = roleRepository.findById(data.getRole().getId()).orElse(null);
        try {
            BeanUtils.copyProperties(data, user, "password", "role");
            user.setPassword(passwordEncoder.encode(data.getPassword()));
//            user.addRole(role);
            user.setRole(role);
            userRepository.save(user);
            log.info("User Save is successfully");
        } catch (Exception ex) {
            log.info("User save is failed");
            log.info("Error : " + ex.getMessage());
        }
        return user;
    }

    @Override
    public User update(User data) {
        log.info("User Update");
        User result = userRepository.findById(data.getId()).orElse(null);
        Role role = roleRepository.findById(data.getRole().getId()).orElse(null);
        if (result != null) {
            result.setPassword(passwordEncoder.encode(data.getPassword()));
            BeanUtils.copyProperties(data, result, "password", "role");
//            result.addRole(role);
            result.setRole(role);
            userRepository.save(result);
            log.info("User Update is successfully");
        } else {
            result = new User();
            log.info("User is Not Found");
        }
        return result;
    }

    @Override
    public User delete(Long id) {
        log.info("User Delete");
        User result = userRepository.findById(id).orElse(null);
        if (result != null) {
            userRepository.delete(result);
            log.info("User Delete is successfully");
        } else {
            result = new User();
            log.info("User is Not Found");
        }
        return result;
    }

    @Override
    public User getById(Long id) {
        log.info("User getById");
        User result = userRepository.findById(id).orElse(null);
        if (result != null) {
            log.info("User getById is successfully");
        } else {
            result = new User();
            log.info("User is Not Found");
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        log.info("User getAll");
        List<User> result = userRepository.findAll();
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public User getByUsername(String username) {
        log.info("User getByUsername");
        User result = userRepository.findByUsername(username);
        if (result != null) {
            log.info("User getByUsername is successfully");
        } else {
            result = new User();
            log.info("User is Not Found");
        }
        return result;
    }
}
