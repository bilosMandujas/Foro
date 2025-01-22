package com.foro.alura.dto.etiquetas.subcategorias;

import com.foro.alura.dto.etiquetas.categorias.DatosResumidosCategoria;
import com.foro.alura.modelo.Etiqueta;

public record DatosListadoSubcategoria(
		Long id,
		String nombre,
		DatosResumidosCategoria categoria
		) {

	public DatosListadoSubcategoria(Etiqueta subcategoria) {
		this(
				subcategoria.getId(),
				subcategoria.getNombre(),
				new DatosResumidosCategoria(subcategoria.getEtiquetaPadre())
			);
	}
}