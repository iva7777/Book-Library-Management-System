package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("application-test.properties")
@Transactional
class AppUserMapperTest {
    private final AppUserMapper appUserMapper = new AppUserMapper();

    @Test
    void shouldCheckIfDtoIsNull() {
        AppUserDto appUserDto = appUserMapper.mapEntityToDto(null);

        Assertions.assertNull(appUserDto);
    }

    @Test
    void shouldCheckIfEntityIsNull() {
        AppUser appUser = appUserMapper.mapDtoToEntity(null);

        Assertions.assertNull(appUser);
    }

    @Test
    void shouldMapEntityToDto() {
        AppUser appUser = new AppUser();
        appUser.setId(1);
        appUser.setUsername("test");
        appUser.setPassword("test");
        appUser.setRole(Role.librarian);

        AppUserDto appUserDto = appUserMapper.mapEntityToDto(appUser);

        Assertions.assertNotNull(appUserDto);
        Assertions.assertEquals(appUser.getId(), appUserDto.id());
        Assertions.assertEquals(appUser.getUsername(), appUserDto.username());
        Assertions.assertEquals(appUser.getPassword(), appUserDto.password());
        Assertions.assertEquals(appUser.getRole(), appUserDto.role());
    }

    @Test
    void shouldMapDtoToEntity() {
        AppUserDto appUserDto = new AppUserDto(1, "test", "test", Role.reader);

        AppUser appUser = appUserMapper.mapDtoToEntity(appUserDto);

        Assertions.assertNotNull(appUser);
        Assertions.assertEquals(appUserDto.id(), appUser.getId());
        Assertions.assertEquals(appUserDto.username(), appUser.getUsername());
        Assertions.assertEquals(appUserDto.password(), appUser.getPassword());
        Assertions.assertEquals(appUserDto.role(), appUser.getRole());
    }
}