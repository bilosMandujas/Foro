package com.foro.alura.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foro.alura.modelo.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>{

	Optional<Rol> findByNombre(String nombre);
}