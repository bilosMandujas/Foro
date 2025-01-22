package com.foro.alura.seguridad;

public record DatosTokensIngreso(
		DatosCompletosToken accessToken, 
		DatosCompletosToken refreshToken
		) {
}