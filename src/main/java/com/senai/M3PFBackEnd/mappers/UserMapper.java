package com.senai.M3PFBackEnd.mappers;

import com.senai.M3PFBackEnd.dtos.User.UserRequestDto;
import com.senai.M3PFBackEnd.dtos.User.UserRequestPutDto;
import com.senai.M3PFBackEnd.entities.UserEntity;
import com.senai.M3PFBackEnd.enums.Genre;
import com.senai.M3PFBackEnd.enums.UserType;

public class UserMapper {
    private UserMapper() {
    }

    public static UserEntity map(UserRequestDto source) {
        UserEntity target = new UserEntity();

        target.setFullName(source.fullName());
        target.setGenre(Genre.valueOf(source.genre()));
        target.setCpf(source.cpf());
        target.setPhone(source.phone());
        target.setEmail(source.email());
        target.setPassword(source.password());
        target.setType(UserType.valueOf(source.type()));

        return target;
    }

    public static UserEntity map(UserRequestPutDto source) {
        UserEntity target = new UserEntity();

        target.setFullName(source.fullName());
        target.setGenre(Genre.valueOf(source.genre()));
        target.setPhone(source.phone());
        target.setPassword(source.password());
        target.setType(UserType.valueOf(source.type()));

        return target;
    }
}
