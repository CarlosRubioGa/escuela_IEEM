package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.ieem.carlos.escuela.Entidad.EntidadCarrera;
import mx.com.ieem.carlos.escuela.Entidad.LlaveCompuestaEntidadCarrera;

// interfaz que define las operaciones de base de datos para la entidad carrera 
public interface RepositorioCarrera extends JpaRepository<EntidadCarrera, LlaveCompuestaEntidadCarrera> {
    
}