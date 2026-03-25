package mx.com.ieem.carlos.escuela.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ieem.carlos.escuela.Entidad.EntidadSistemasEscolares;

// interfaz que define las operaciones de base de datos para la entidad sistemas escolares
public interface RepositorioSistemasEscolares extends JpaRepository<EntidadSistemasEscolares, Long> {
    
}