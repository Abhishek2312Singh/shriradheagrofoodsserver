package com.srap.Shree.Radhe.Agro.Foods.repository;

import com.srap.Shree.Radhe.Agro.Foods.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
