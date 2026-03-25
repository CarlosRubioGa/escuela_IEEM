package mx.com.ieem.carlos.escuela.Servicio;

// Interfaz que define los metodos para gestionar la relacion curso-maestro
public interface ServicioCursoMaestro {

    // Funcion que asigna un curso a un maestro
    void asignarCursoMaestro(Long idMaestro, Long idCurso) throws Exception;

    // Funcion que actualizar un curso de un maestro
    void actualizarCursoMaestro(Long idCurso, Long idMaestro) throws Exception;;
}