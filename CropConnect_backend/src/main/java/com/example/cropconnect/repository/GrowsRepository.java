package com.example.cropconnect.repository;

import com.example.cropconnect.entity.Grows;
import com.example.cropconnect.entity.GrowsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrowsRepository extends JpaRepository<Grows, GrowsId> {}
