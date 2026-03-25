package mx.com.ieem.carlos.escuela.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.ieem.carlos.escuela.Entidad.EntidadMateria;

// Interfaz que define las operaciones de base de datos para la entidad materia
public interface RepositorioMateria extends JpaRepository<EntidadMateria, Long> {

    // JPQL: Obtiene todas las materias registradas
    @Query("SELECT m FROM EntidadMateria m")
    List<EntidadMateria> findAllMaterias();

    // JPQL: Obtiene una materia por su ID
    @Query("SELECT m FROM EntidadMateria m WHERE m.id_materia = :id")
    Optional<EntidadMateria> findMateriaById(@Param("id") Long id);

}