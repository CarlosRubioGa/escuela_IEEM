package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.ieem.carlos.escuela.Entidad.EntidadMaestro;

// interfaz que define las operaciones de base de datos para la entidad maestro
public interface RepositorioMaestro extends JpaRepository<EntidadMaestro, Long> {
     
}