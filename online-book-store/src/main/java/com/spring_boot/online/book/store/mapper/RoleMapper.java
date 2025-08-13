package com.spring_boot.online.book.store.mapper;

import com.spring_boot.online.book.store.dto.RoleDTO;
import com.spring_boot.online.book.store.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    // role dto to role entity
    public Role mapToRoleEntity (RoleDTO roleDTO){

        if (roleDTO == null){
            return null;
        }
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());

        return role;

    }

    public RoleDTO mapToRoleDTO (Role role){
        if (role == null){
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
