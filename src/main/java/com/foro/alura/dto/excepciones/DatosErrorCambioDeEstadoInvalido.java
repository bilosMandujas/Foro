package com.foro.alura.dto.excepciones;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.foro.alura.excepciones.CambioDeEstadoInvalidoException;

public record DatosErrorCambioDeEstadoInvalido(
		int codigoDeError,
		HttpStatus status,
		LocalDateTime fechaYHora,
		String mensaje) {

	public DatosErrorCambioDeEstadoInvalido(CambioDeEstadoInvalidoException excepcion) {
		this(400, HttpStatus.BAD_REQUEST, LocalDateTime.now(), excepcion.getMessage());		
	}
}