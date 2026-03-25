package mx.com.ieem.carlos.escuela.Servicio;

import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadMaestro;

// Interfaz que define los metodos disponibles para gestionar los maestros
public interface ServicioMaestro {
    
    // Función que guarda la información de un maestro en el sistema
    void guardarMaestro(EntidadMaestro maestro) throws Exception;
    
    // Funcion que recupera la lista completa de todos los maestros registrados
    List<EntidadMaestro> obtenerTodosLosMaestros() throws Exception;

    // Función que obtiene un maestro por su ID 
    EntidadMaestro obtenerMaestroPorId(Long id) throws Exception;
}