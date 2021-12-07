package com.example.shein.repository;

import com.example.shein.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,Long> {

    @Override
    List<BrandEntity> findAll();
    Optional<BrandEntity> findByName(String name);
}
