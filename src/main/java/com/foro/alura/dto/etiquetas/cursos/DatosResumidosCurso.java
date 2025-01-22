package com.foro.alura.dto.etiquetas.cursos;

import com.foro.alura.modelo.Etiqueta;

public record DatosResumidosCurso (Long id, String nombre) {

	public DatosResumidosCurso(Etiqueta curso) {
		this(curso.getId(), curso.getNombre());
	}
}