package com.example.cropconnect.repository;

import com.example.cropconnect.entity.FarmPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmPlotRepository extends JpaRepository<FarmPlot, Integer> {

}