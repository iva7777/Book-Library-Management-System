package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ActiveProfiles("application-test.properties")
@SpringBootTest
@Transactional
class AppUserRepositoryTest {
    @Autowired
    AppUserRepository appUserRepository;

    private AppUser appUser;

    @BeforeEach
    void setup() {
        appUser = new AppUser();
        appUser.setId(1);
        appUser.setUsername("user100");
        appUser.setPassword("Qwer123");
        appUser.setRole(Role.librarian);
    }

    @Test
    void shouldFindAllUsers() {
        appUserRepository.save(appUser);

        List<AppUser> userList = appUserRepository.findAll();

        assertThat(userList)
                .isNotEmpty()
                .hasSizeGreaterThan(1);

    }

    @Test
    void shouldFindUserById() {
        appUserRepository.save(appUser);
        assertThat(appUserRepository.findById(appUser.getId())).isNotEmpty();
    }

    @Test
    void shouldCreateUser() {
        AppUser savedUser = appUserRepository.save(appUser);
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void shouldDeleteUser() {
        appUserRepository.save(appUser);
        appUserRepository.delete(appUser);
        Optional<AppUser> deleted = appUserRepository.findById(appUser.getId());
        assertThat(deleted).isEmpty();
    }

    @Test
    void shouldFindByUsername() {
        AppUser user1 = new AppUser();
        user1.setUsername("user100");
        user1.setPassword("Qwer123");
        user1.setRole(Role.reader);
        appUserRepository.save(user1);

        AppUser user2 = new AppUser();
        user2.setUsername("user200");
        user2.setPassword("Abcd123");
        user2.setRole(Role.reader);
        appUserRepository.save(user2);

        Optional<AppUser> result = appUserRepository.findByUsername("user100");

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(user -> {
                    assertThat(user.getUsername()).isEqualTo("user100");
                });
    }

    @Test
    void shouldFindByRole() {
        AppUser librarianUser = new AppUser();
        librarianUser.setUsername("librarianUser");
        librarianUser.setPassword("Librarian123");
        librarianUser.setRole(Role.librarian);
        appUserRepository.save(librarianUser);

        List<AppUser> result = appUserRepository.findByRole(Role.librarian);

        assertThat(result)
                .isNotEmpty()
                .extracting(AppUser::getRole)
                .containsOnly(Role.librarian);
    }
}