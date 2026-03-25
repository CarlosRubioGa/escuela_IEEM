package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.ieem.carlos.escuela.Entidad.EntidadDivision;

// interfaz que define las operaciones de base de datos para la entidad division
public interface RepositorioDivision extends JpaRepository<EntidadDivision, Long> {
    
}