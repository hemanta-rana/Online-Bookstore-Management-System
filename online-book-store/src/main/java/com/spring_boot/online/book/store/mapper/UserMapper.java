package com.spring_boot.online.book.store.mapper;

import com.spring_boot.online.book.store.dto.UserDTO;
import com.spring_boot.online.book.store.model.Role;
import com.spring_boot.online.book.store.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO mapToUserDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(null); // Never expose password in DTO
        dto.setEmail(user.getEmail());

        // Convert Role entities to Set<String> of role names
        Set<String> roleNames = (user.getRoles() != null)
                ? user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet())
                : Collections.emptySet();
        dto.setRoles(roleNames);

        return dto;
    }

    /**
     * Map DTO to Entity (roles are provided by service layer)
     */
    public User mapToUserEntity(UserDTO dto, Set<Role> roles) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // Service should encode
        user.setEmail(dto.getEmail());
        user.setRoles(roles != null ? roles : Collections.emptySet());

        return user;
    }
}
