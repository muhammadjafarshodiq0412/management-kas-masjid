package com.managementkasmasjid.service.impl;

import com.managementkasmasjid.entity.Role;
import com.managementkasmasjid.repository.RoleRepository;
import com.managementkasmasjid.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role data) {
        log.info("Role Save");
        try {
            roleRepository.save(data);
            log.info("Role Save is successfully");
        }catch (Exception ex){
            log.info("Role save is failed");
            log.info("Error : "+ ex.getMessage());
        }
        return data;
    }

    @Override
    public Role update(Role data) {
        log.info("Role Update");
       Role result = roleRepository.findById(data.getId()).orElse(null);
        if(result != null){
            BeanUtils.copyProperties(data,result);
            roleRepository.save(result);
            log.info("Role Update is successfully");
        }else {
            result = new Role();
            log.info("Role is Not Found");
        }
        return result;
    }

    @Override
    public Role delete(Long id) {
        log.info("Role Delete");
        Role result = roleRepository.findById(id).orElse(null);
        if(result != null){
            roleRepository.delete(result);
            log.info("Role Delete is successfully");
        }else {
            result = new Role();
            log.info("Role is Not Found");
        }
        return result;
    }

    @Override
    public Role getById(Long id) {
        log.info("Role getById");
        Role result = roleRepository.findById(id).orElse(null);
        if(result != null){
            log.info("Data : "+result);
            log.info("Role getById is successfully");
        }else {
            result = new Role();
            log.info("Role is Not Found");
        }
        return result;
    }

    @Override
    public List<Role> getAll() {
        log.info("Role getAll");
        List<Role> result = roleRepository.findAll();
        log.info("Data :: "+result.size());
        return result;
    }
}
