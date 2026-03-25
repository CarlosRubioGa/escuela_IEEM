package mx.com.ieem.carlos.escuela.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ieem.carlos.escuela.Entidad.EntidadDatosPersonales;

// interfaz que define las operaciones de base de datos para los datos personales
public interface RepositorioDatosPersonales extends JpaRepository<EntidadDatosPersonales, Long> {

}