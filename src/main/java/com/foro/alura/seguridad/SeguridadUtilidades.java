package com.foro.alura.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.foro.alura.modelo.Publicacion;
import com.foro.alura.modelo.Respuesta;
import com.foro.alura.modelo.Usuario;
import com.foro.alura.repositorio.PublicacionRepositorio;
import com.foro.alura.repositorio.RespuestaRepositorio;

@Component
public class SeguridadUtilidades {

	@Autowired
	PublicacionRepositorio publicacionRepositorio;

	@Autowired
	RespuestaRepositorio respuestaRepositorio;

	public static Usuario getUsuarioAutenticado() {
		var datosAutenticacion = SecurityContextHolder.getContext().getAuthentication();
		UsuarioSeguridad usuarioSeguridad = (UsuarioSeguridad) datosAutenticacion.getPrincipal();
		return usuarioSeguridad.getUsuario();
	}

	public boolean esAutor(Long recursoId, String clase) {
		String correoAutorAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

		if(Publicacion.class.getSimpleName().equals(clase)) {

			Publicacion publicacion = publicacionRepositorio.getReferenceById(recursoId);
			String correoAutorPublicacion = publicacion.getAutor().getCorreo(); 
			return correoAutorAutenticado.equals(correoAutorPublicacion);

		} else if (Respuesta.class.getSimpleName().equals(clase)) {

			Respuesta respuesta = respuestaRepositorio.getReferenceById(recursoId);
			 String correoAutorRespuesta = respuesta.getAutor().getCorreo(); 

			return correoAutorAutenticado.equals(correoAutorRespuesta);
		}
		return false;
	}
}