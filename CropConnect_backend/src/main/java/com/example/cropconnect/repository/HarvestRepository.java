package com.example.cropconnect.repository;

import com.example.cropconnect.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Integer> {

}