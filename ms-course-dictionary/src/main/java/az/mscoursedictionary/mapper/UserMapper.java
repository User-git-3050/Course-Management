package az.mscoursedictionary.mapper;

import az.mscoursedictionary.dao.UserRequest;
import az.mscoursedictionary.dao.UserResponse;
import az.mscoursedictionary.entity.UserEntity;
import az.mscoursedictionary.enums.RoleEnum;

public enum UserMapper {
    USER_MAPPER;

    public UserResponse mapToResponse(UserEntity userEntity){
        return UserResponse.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .password(userEntity.getPassword())
                .phone(userEntity.getPhone())
                .role(String.valueOf(userEntity.getRole()))
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .build();

    }

}
