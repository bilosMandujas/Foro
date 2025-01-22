package com.foro.alura.dto.etiquetas.subcategorias;

import java.util.List;

import com.foro.alura.dto.etiquetas.categorias.DatosResumidosCategoria;
import foro.dto.etiquetas.cursos.DatosResumidosCurso;
import com.foro.alura.modelo.Etiqueta;

public record DatosCompletosSubcategoria (
		Long id,
		String nombre,
		DatosResumidosCategoria categoria,
		List<DatosResumidosCurso> cursos
		) {

	public DatosCompletosSubcategoria(Etiqueta subcategoria) {
		this(
				subcategoria.getId(),
				subcategoria.getNombre(),
				new DatosResumidosCategoria(subcategoria.getEtiquetaPadre()),
				subcategoria.getEtiquetasHijas().stream().map(DatosResumidosCurso::new).toList()
			);
	}
}