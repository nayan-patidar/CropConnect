package com.example.cropconnect.repository;

import com.example.cropconnect.entity.ControlledBy;
import com.example.cropconnect.entity.ControlledById;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlledByRepository extends JpaRepository<ControlledBy, ControlledById> {}
