package com.example.cropconnect.repository;

import com.example.cropconnect.entity.BelongsTo;
import com.example.cropconnect.entity.BelongsToId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BelongsToRepository extends JpaRepository<BelongsTo, BelongsToId> {}
