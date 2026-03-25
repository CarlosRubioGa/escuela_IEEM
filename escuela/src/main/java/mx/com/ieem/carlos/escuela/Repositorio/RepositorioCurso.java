package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ieem.carlos.escuela.Entidad.EntidadCurso;

// Interfaz que define las operaciones de base de datos para la entidad curso
public interface RepositorioCurso extends JpaRepository<EntidadCurso, Long> {

}