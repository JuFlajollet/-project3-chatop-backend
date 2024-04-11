package com.chatop.mapper;

import com.chatop.dto.DBUserDTO;
import com.chatop.model.DBUser;
import org.springframework.stereotype.Component;

@Component
public class DBUserMapper {
    public DBUserDTO toDBUserDTO(DBUser user) {
        if(user == null){
            return null;
        }

        return DBUserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public DBUser toDBUser(DBUserDTO userDTO){
        if(userDTO == null){
            return null;
        }

        return DBUser.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .build();
    }
}
