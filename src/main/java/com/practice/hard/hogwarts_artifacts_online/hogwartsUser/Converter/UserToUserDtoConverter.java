package com.practice.hard.hogwarts_artifacts_online.hogwartsUser.Converter;

import com.practice.hard.hogwarts_artifacts_online.hogwartsUser.Dto.UserDto;
import com.practice.hard.hogwarts_artifacts_online.hogwartsUser.HogwartsUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<HogwartsUser, UserDto> {

    @Override
    public UserDto convert(HogwartsUser source) {

        return new UserDto(source.getId(), source.getName(), source.isEnabled(), source.getRoles());
    }

}
