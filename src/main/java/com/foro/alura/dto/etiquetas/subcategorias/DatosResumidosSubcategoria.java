package com.foro.alura.dto.etiquetas.subcategorias;

import com.foro.alura.modelo.Etiqueta;

public record DatosResumidosSubcategoria(
		Long id,
		String nombre
		) {

	public DatosResumidosSubcategoria(Etiqueta subcategoria) {
		this(
				subcategoria.getId(),
				subcategoria.getNombre()
			);
	}
}