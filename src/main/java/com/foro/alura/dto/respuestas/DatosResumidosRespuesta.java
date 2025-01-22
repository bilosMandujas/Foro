package com.foro.alura.dto.respuestas;

import java.time.LocalDateTime;

import com.foro.alura.modelo.Respuesta;

public record DatosResumidosRespuesta(Long id, String mensaje, LocalDateTime fechaCreacion, Boolean solucion) {

	public DatosResumidosRespuesta(Respuesta respuesta) {
		this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getSolucion());		
	}
}