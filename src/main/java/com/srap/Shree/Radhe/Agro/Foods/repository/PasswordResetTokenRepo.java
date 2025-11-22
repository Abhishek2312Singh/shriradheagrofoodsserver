package com.srap.Shree.Radhe.Agro.Foods.repository;

import com.srap.Shree.Radhe.Agro.Foods.entity.PasswordResetToken;
import com.srap.Shree.Radhe.Agro.Foods.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByUser(User user);
    PasswordResetToken findByToken(String token);
}