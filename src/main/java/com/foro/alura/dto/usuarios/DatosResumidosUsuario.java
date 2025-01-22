package com.foro.alura.dto.usuarios;

import com.foro.alura.modelo.Usuario;

public record DatosResumidosUsuario(
		Long usuarioId,
		String nombre,
		String correo) {

	public DatosResumidosUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNombre(), usuario.getCorreo());
	}
}