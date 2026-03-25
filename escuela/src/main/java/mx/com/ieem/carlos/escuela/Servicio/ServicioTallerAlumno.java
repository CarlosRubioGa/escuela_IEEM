package mx.com.ieem.carlos.escuela.Servicio;

// Interfaz que define los metodos para gestionar la relacion taller-alumno
public interface ServicioTallerAlumno {

    // Funcion que asigna un taller a un alumno
    void asignarTallerAlumno(Long idTaller, Long idAlumno) throws Exception;

    // Funcion que elimina la relacion entre un taller y un alumno
    void eliminarTallerAlumno(Long idTaller, Long idAlumno) throws Exception;
}