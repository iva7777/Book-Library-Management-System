package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);

    List<AppUser> findByRole(Role role);
}
