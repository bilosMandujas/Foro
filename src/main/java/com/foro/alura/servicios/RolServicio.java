package com.foro.alura.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foro.alura.dto.roles.DatosRol;
import com.foro.alura.modelo.Rol;
import com.foro.alura.repositorio.RolRepositorio;

@Service
public class RolServicio {

	@Autowired
	private RolRepositorio rolRepositorio;

	public void crearRol(DatosRol datosRol) {
		String nombre = datosRol.nombre().toUpperCase();
		Optional<Rol> rol = rolRepositorio.findByNombre(nombre);

		if(rol.isPresent()) {
			throw new RuntimeException("Este nombre de rol ya existe");
		}

		Rol nuevoRol = new Rol();
		nuevoRol.setNombre(nombre);

		rolRepositorio.save(nuevoRol);
	}
}