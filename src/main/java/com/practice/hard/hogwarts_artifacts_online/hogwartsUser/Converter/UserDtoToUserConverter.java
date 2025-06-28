package com.practice.hard.hogwarts_artifacts_online.hogwartsUser.Converter;

import com.practice.hard.hogwarts_artifacts_online.hogwartsUser.Dto.UserDto;
import com.practice.hard.hogwarts_artifacts_online.hogwartsUser.HogwartsUser;
import org.springframework.core.convert.converter.Converter;

public class UserDtoToUserConverter implements Converter<UserDto, HogwartsUser> {

    @Override
    public HogwartsUser convert(UserDto source) {

        HogwartsUser hogwartsUser= new HogwartsUser();
        hogwartsUser.setId(source.id());
        hogwartsUser.setName(source.name());
        hogwartsUser.setEnabled(source.enabled());
        hogwartsUser.setRoles(source.roles());

        return hogwartsUser;
    }
}
