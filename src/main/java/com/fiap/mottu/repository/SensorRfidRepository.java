package com.fiap.mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fiap.mottu.model.SensorRfid;

public interface SensorRfidRepository extends JpaRepository<SensorRfid, Integer> { }
