package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AppUserMapper {
    public AppUserDto mapEntityToDto(AppUser appUser){
        if (appUser == null){
            return null;
        }

        Integer id = null;
        String username = null;
        String password = null;
        Role role = null;
        //Reader reader = null;

        id = appUser.getId();
        username = appUser.getUsername();
        password = appUser.getPassword();
        role = appUser.getRole();
        //reader = appUser.getReader();

        return new AppUserDto(id, username, password, role);
    }

    public AppUser mapDtoToEntity(AppUserDto appUserDto){
        if (appUserDto == null) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setId(appUserDto.id());
        appUser.setUsername(appUserDto.username());
        appUser.setPassword(appUserDto.password());
        appUser.setRole(appUserDto.role());
        //appUser.setReader(appUserDto.reader());

        return appUser;
    }
}
