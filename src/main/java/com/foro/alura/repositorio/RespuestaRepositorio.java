package com.foro.alura.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.foro.alura.modelo.Respuesta;

public interface RespuestaRepositorio extends JpaRepository<Respuesta, Long> {

	Page<Respuesta> findAllByPublicacionId(Long publicacion_id, Pageable paginacion);
}