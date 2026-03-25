package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.ieem.carlos.escuela.Entidad.EntidadTallerAlumno;
import mx.com.ieem.carlos.escuela.Entidad.LlaveCompuestaEntidadTallerAlumno;

// interfaz que define las operaciones de base de datos para la entidad EntidadTallerAlumno, usando la llave compuesta LlaveCompuestaEntidadTallerAlumno
public interface RepositorioTallerAlumno extends JpaRepository<EntidadTallerAlumno, LlaveCompuestaEntidadTallerAlumno> {
    
}