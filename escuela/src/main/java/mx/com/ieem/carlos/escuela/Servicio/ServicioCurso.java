package mx.com.ieem.carlos.escuela.Servicio;

import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadCurso;

// Interfaz que define los metodos disponibles para gestionar los cursos
public interface ServicioCurso {

    // Funcion que guarda la informacion de un nuevo curso en el sistema
    void guardarCurso(EntidadCurso entidadCurso) throws Exception;

    // Funcion que obtiene la lista de todos los cursos registrados en el sistema
    List<EntidadCurso> obtenerTodosLosCursos() throws Exception;

    // Funcion que obtiene un curso por su ID
    EntidadCurso obtenerCursoPorId(Long id) throws Exception;
}