package mx.com.ieem.carlos.escuela.Servicio;

import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadAlumno;

// Interfaz que define los metodos disponibles para gestionar los alumnos
public interface ServicioAlumno {
    
    // Función que guarda la información de un alumno en el sistema
    void guardarAlumno(EntidadAlumno alumno) throws Exception;
    
    // Funcion que recupera la lista completa de todos los alumnos registrados
    List<EntidadAlumno> obtenerTodosLosAlumnos() throws Exception;

    // Función que obtiene un alumno por su ID
    EntidadAlumno obtenerAlumnoPorId(Long id) throws Exception;
}