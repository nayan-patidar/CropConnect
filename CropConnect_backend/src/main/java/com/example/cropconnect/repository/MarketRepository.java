package com.example.cropconnect.repository;

import com.example.cropconnect.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer> {

}