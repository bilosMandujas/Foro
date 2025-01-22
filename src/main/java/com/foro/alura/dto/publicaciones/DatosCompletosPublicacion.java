package com.foro.alura.dto.publicaciones;

import java.time.LocalDateTime;
import java.util.List;

import com.foro.alura.dto.respuestas.DatosResumidosRespuesta;
import com.foro.alura.dto.usuarios.DatosResumidosUsuario;
import com.foro.alura.modelo.EstadoPublicacion;
import com.foro.alura.modelo.Publicacion;

public record DatosCompletosPublicacion(
		Long publicacionId, 
		String titulo, 
		String mensaje, 
		LocalDateTime fechaCreacion, 
		EstadoPublicacion estado, 
		List<DatosResumidosRespuesta> respuestas,
		Long cursoId,
		DatosResumidosUsuario autor
		) {

	public DatosCompletosPublicacion(Publicacion publicacion) {
		this(
				publicacion.getId(), 
				publicacion.getTitulo(), 
				publicacion.getMensaje(), 
				publicacion.getFechaCreacion(), 
				publicacion.getEstado(), 
				publicacion.getRespuestas().stream().map(DatosResumidosRespuesta::new).toList(),
				publicacion.getCurso().getId(),
				new DatosResumidosUsuario(publicacion.getAutor())
			);
	}
}