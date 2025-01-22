package com.foro.alura.dto.etiquetas.cursos;

import com.foro.alura.dto.etiquetas.categorias.DatosResumidosCategoria;
import foro.dto.etiquetas.subcategorias.DatosResumidosSubcategoria;
import com.foro.alura.modelo.Etiqueta;

public record DatosCompletosCurso (
		Long id,
		String nombre,
		DatosResumidosCategoria categoria,
		DatosResumidosSubcategoria subcategoria
	){

	public DatosCompletosCurso(Etiqueta curso) {
		this(
				curso.getId(),
				curso.getNombre(),
				new DatosResumidosCategoria(curso.getEtiquetaPadre().getEtiquetaPadre()),
				new DatosResumidosSubcategoria(curso.getEtiquetaPadre())
			);
	}
}