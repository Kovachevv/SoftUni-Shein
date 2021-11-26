package com.example.shein.repository;

import com.example.shein.model.entity.ClothingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingRepository extends JpaRepository<ClothingEntity,Long> {

}
