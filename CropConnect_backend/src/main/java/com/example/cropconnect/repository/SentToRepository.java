package com.example.cropconnect.repository;

import com.example.cropconnect.entity.SentTo;
import com.example.cropconnect.entity.SentToId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentToRepository extends JpaRepository<SentTo, SentToId> {}
