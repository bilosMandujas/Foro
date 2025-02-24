package com.foro.alura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foro.alura.dto.roles.DatosRol;
import com.foro.alura.servicios.RolServicio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/roles")
public class RolControlador {

	@Autowired
	private RolServicio rolServicio;

	@PostMapping
	public ResponseEntity<Object> crearRol(@RequestBody @Valid DatosRol datosRol) {
		rolServicio.crearRol(datosRol);
		return ResponseEntity.ok().build();
	}
}