package com.foro.alura.dto.publicaciones;

import java.time.LocalDateTime;

import com.foro.alura.modelo.EstadoPublicacion;
import com.foro.alura.modelo.Publicacion;

public record DatosResumidosPublicacion(
		Long publicacionId,
		String titulo,
		String mensaje,
		LocalDateTime fechaCreacion,
		EstadoPublicacion estado,
		int totalRespuestas,
		Long cursoId
	) {

	public DatosResumidosPublicacion(Publicacion publicacion) {
		this(
				publicacion.getId(),
				publicacion.getTitulo(),
				publicacion.getMensaje(),
				publicacion.getFechaCreacion(),
				publicacion.getEstado(),
				publicacion.calcularTotalRespuestas(),
				publicacion.getCurso().getId()
				);
	}
}