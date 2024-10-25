package com.example.userapi.dto;

import com.example.userapi.entity.Authority;
import com.example.userapi.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-28T22:46:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.getUsername() );
        user.setEnabled( dto.getEnabled() );
        List<Authority> list = dto.getAuthority();
        if ( list != null ) {
            user.setAuthority( new ArrayList<Authority>( list ) );
        }

        return user;
    }

    @Override
    public UserDto toDTO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.username( entity.getUsername() );
        userDto.enabled( entity.getEnabled() );
        List<Authority> list = entity.getAuthority();
        if ( list != null ) {
            userDto.authority( new ArrayList<Authority>( list ) );
        }

        return userDto.build();
    }
}
