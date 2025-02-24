package com.foro.alura.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.foro.alura.modelo.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {

	Page<Publicacion> findAllByCursoId(Long cursoId, Pageable paginacion);
}