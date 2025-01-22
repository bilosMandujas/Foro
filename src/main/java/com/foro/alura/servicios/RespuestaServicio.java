package com.foro.alura.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.foro.alura.dto.respuestas.DatosGuardarRespuesta;
import com.foro.alura.excepciones.CambioDeEstadoInvalidoException;
import com.foro.alura.excepciones.PertenenciaInvalidaExcepcion;
import com.foro.alura.excepciones.RecursoNoEncontradoException;
import com.foro.alura.excepciones.TransaccionSobreEntidadInexistenteException;
import com.foro.alura.dto.respuestas.DatosCompletosRespuesta;
import com.foro.alura.modelo.EstadoPublicacion;
import com.foro.alura.modelo.Publicacion;
import com.foro.alura.modelo.Respuesta;
import com.foro.alura.modelo.Usuario;
import com.foro.alura.repositorio.PublicacionRepositorio;
import com.foro.alura.repositorio.RespuestaRepositorio;
import com.foro.alura.seguridad.SeguridadUtilidades;

@Service
public class RespuestaServicio {

	@Autowired
	private RespuestaRepositorio respuestaRepositorio;

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	public DatosCompletosRespuesta crearRespuesta(Long publicacionId, DatosGuardarRespuesta datosRespuesta) {
		if(!publicacionRepositorio.existsById(publicacionId)) {
			throw new TransaccionSobreEntidadInexistenteException("La publicación de id " + publicacionId + " no existe");
		}

		Publicacion publicacion = publicacionRepositorio.getReferenceById(publicacionId);

		if(publicacion.getEstado().equals(EstadoPublicacion.SOLUCIONADO)) {
			throw new CambioDeEstadoInvalidoException("No se puede crear esta respuesta, "
					+ "pues la publicación de id " + publicacionId + " ya fue solucionada");
		}

		if(publicacion.getEstado().equals(EstadoPublicacion.NO_RESPONDIDO)) {
			publicacion.setEstado(EstadoPublicacion.NO_SOLUCIONADO);
		}

		Usuario usuario = SeguridadUtilidades.getUsuarioAutenticado();

		Respuesta respuesta = new Respuesta();
		respuesta.setMensaje(datosRespuesta.mensaje());
		respuesta.setPublicacion(publicacion);
		respuesta.setAutor(usuario);

		respuestaRepositorio.save(respuesta);

		return new DatosCompletosRespuesta(respuesta);
	}

	public Page<DatosCompletosRespuesta> listarRespuestasPorPublicacionId(Long publicacionId, Pageable paginacion) {
		if(!publicacionRepositorio.existsById(publicacionId)) {
			throw new RecursoNoEncontradoException("No fue posible encontrar la publicación de id: " + publicacionId);
		}

		return respuestaRepositorio.findAllByPublicacionId(publicacionId, paginacion).map(DatosCompletosRespuesta::new);
	}

	@PreAuthorize("@seguridadUtilidades.esAutor(#respuestaId, 'Respuesta')")
	public DatosCompletosRespuesta editarRespuesta(
			Long publicacionId, 
			Long respuestaId,
			DatosGuardarRespuesta datosRespuesta) {

		if(!publicacionRepositorio.existsById(publicacionId)) {
			throw new TransaccionSobreEntidadInexistenteException("La publicación de id " + publicacionId + " no existe");
		}

		if(!respuestaRepositorio.existsById(respuestaId)) {
			throw new TransaccionSobreEntidadInexistenteException("La respuesta de id " + respuestaId + " no existe");
		}

		Respuesta respuesta = respuestaRepositorio.getReferenceById(respuestaId);

		if(!respuesta.getPublicacion().getId().equals(publicacionId)) {
			throw new PertenenciaInvalidaExcepcion("La respuesta de id " + respuestaId 
					+ " no pertenece a la publicación de id " + publicacionId);
		}

		respuesta.setMensaje(datosRespuesta.mensaje());

		return new DatosCompletosRespuesta(respuesta);
	}

	@PreAuthorize("@seguridadUtilidades.esAutor(#publicacionId, 'Publicacion')")
	public void escogerRespuestaComoSolucion(Long publicacionId, Long respuestaId) {
		if(!publicacionRepositorio.existsById(publicacionId)) {
			throw new TransaccionSobreEntidadInexistenteException("La publicación de id " + publicacionId + " no existe");
		}

		Publicacion publicacion = publicacionRepositorio.getReferenceById(publicacionId);

		if(publicacion.getEstado().equals(EstadoPublicacion.SOLUCIONADO)) {
			throw new CambioDeEstadoInvalidoException("No se puede crear esta respuesta, "
					+ "pues la publicación de id " + publicacionId + " ya fue solucionada");
		}

		if(!respuestaRepositorio.existsById(respuestaId)) {
			throw new TransaccionSobreEntidadInexistenteException("La respuesta de id " + respuestaId + " no existe");
		}

		Respuesta respuesta = respuestaRepositorio.getReferenceById(respuestaId);

		if(!respuesta.getPublicacion().getId().equals(publicacionId)) {
			throw new PertenenciaInvalidaExcepcion("La respuesta de id " + respuestaId 
					+ " no pertenece a la publicación de id " + publicacionId);
		}

		publicacion.setEstado(EstadoPublicacion.SOLUCIONADO);
		respuesta.setSolucion(true);
	}

}