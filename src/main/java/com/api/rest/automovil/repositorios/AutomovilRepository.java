package com.api.rest.automovil.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.automovil.entidades.Automovil;

public interface AutomovilRepository extends JpaRepository<Automovil, Integer>{

}
