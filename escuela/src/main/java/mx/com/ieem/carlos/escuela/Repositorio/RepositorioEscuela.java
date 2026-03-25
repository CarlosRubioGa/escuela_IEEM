package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.ieem.carlos.escuela.Entidad.EntidadEscuela;

// Repositorio que extiende JpaRepository para operaciones CRUD de Escuela
public interface RepositorioEscuela extends JpaRepository<EntidadEscuela, Long> {
    
}
