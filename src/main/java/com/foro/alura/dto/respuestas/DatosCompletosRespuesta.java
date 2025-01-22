package com.foro.alura.dto.respuestas;

import java.time.LocalDateTime;

import com.foro.alura.dto.usuarios.DatosResumidosUsuario;
import com.foro.alura.modelo.Respuesta;

public record DatosCompletosRespuesta(
		Long id, 
		String mensaje, 
		LocalDateTime fechaCreacion, 
		Boolean solucion, 
		Long publicacion_id,
		DatosResumidosUsuario autor) {

	public DatosCompletosRespuesta(Respuesta respuesta) {
		this(
				respuesta.getId(),
				respuesta.getMensaje(),
				respuesta.getFechaCreacion(),
				respuesta.getSolucion(),
				respuesta.getPublicacion().getId(),
				new DatosResumidosUsuario(respuesta.getAutor()));		
	}
}