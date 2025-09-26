package com.srap.Shree.Radhe.Agro.Foods.repository;

import com.srap.Shree.Radhe.Agro.Foods.entity.UserQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQueryRepository extends JpaRepository<UserQuery,Long> {
}
