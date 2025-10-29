package com.fiap.mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fiap.mottu.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer> {}
