package com.foro.alura.dto.etiquetas.categorias;

import com.foro.alura.modelo.Etiqueta;

public record DatosResumidosCategoria(Long id, String nombre) {

	public DatosResumidosCategoria(Etiqueta categoria) {
		this(categoria.getId(), categoria.getNombre());
	}
}