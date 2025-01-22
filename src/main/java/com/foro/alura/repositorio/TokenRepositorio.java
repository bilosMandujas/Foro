package com.foro.alura.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.foro.alura.modelo.RefreshToken;
import com.foro.alura.modelo.Usuario;
import jakarta.transaction.Transactional;

public interface TokenRepositorio extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String refreshToken);

	@Transactional
	@Modifying
	void deleteByUsuario(Usuario usuario);
}