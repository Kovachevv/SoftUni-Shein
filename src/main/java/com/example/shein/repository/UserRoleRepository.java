package com.example.shein.repository;

import com.example.shein.model.entity.UserRoleEntity;
import com.example.shein.model.enums.UserRoleEnum;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(UserRoleEnum role);
}
