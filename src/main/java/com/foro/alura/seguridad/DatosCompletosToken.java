package com.foro.alura.seguridad;

import java.util.Date;

public record DatosCompletosToken(
		String token, 
		Date fecha_expiracion) {
}