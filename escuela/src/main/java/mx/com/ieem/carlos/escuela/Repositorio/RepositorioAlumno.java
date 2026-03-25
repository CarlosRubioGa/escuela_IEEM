package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.ieem.carlos.escuela.Entidad.EntidadAlumno;


// interfaz que define las operaciones de base de datos para la entidad alumno 
public interface RepositorioAlumno extends JpaRepository<EntidadAlumno, Long> {
    
}