package com.example.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.Entity.AplicacionModel;

public interface IAplicacionRepository extends JpaRepository<AplicacionModel, Long> {
	Optional<AplicacionModel> findByAplicacion(String nombre);

	}
