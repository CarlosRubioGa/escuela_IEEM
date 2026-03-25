package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ieem.carlos.escuela.Entidad.EntidadTaller;

// Interfaz que define las operaciones de base de datos para la entidad taller
public interface RepositorioTaller extends JpaRepository<EntidadTaller, Long> {

}