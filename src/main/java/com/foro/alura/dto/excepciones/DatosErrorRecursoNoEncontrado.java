package com.foro.alura.dto.excepciones;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.foro.alura.excepciones.RecursoNoEncontradoException;

public record DatosErrorRecursoNoEncontrado(
		int codigoDeError,
		HttpStatus status,
		LocalDateTime fechaYHora,
		String mensaje) {

	public DatosErrorRecursoNoEncontrado(RecursoNoEncontradoException excepcion) {
		this(404, HttpStatus.NOT_FOUND, LocalDateTime.now(), excepcion.getMessage());		
	}
}