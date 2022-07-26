package ru.bainc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bainc.model.Role;
import ru.bainc.repositories.RoleRepository;
import ru.bainc.repositories.UserRepository;

import java.util.List;

@Service
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role getById(Long id){
        return  roleRepository.getById(id);
    }

    public Role getByName(String name){
        return roleRepository.findByName(name);
    }

}
