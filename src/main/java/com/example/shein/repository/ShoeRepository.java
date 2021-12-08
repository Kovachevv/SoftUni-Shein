package com.example.shein.repository;

import com.example.shein.model.entity.ShoeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends JpaRepository<ShoeEntity,Long> {
}
