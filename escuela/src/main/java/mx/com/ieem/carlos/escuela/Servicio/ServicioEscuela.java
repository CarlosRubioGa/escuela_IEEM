package mx.com.ieem.carlos.escuela.Servicio;
import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadEscuela;

// Interfaz que define los metodos disponibles para gestionar las escuelas
public interface ServicioEscuela {
    
    // funcion que guarda la información de una escuela en el sistema
    void guardarEscuela(EntidadEscuela escuelaDto) throws Exception;
    
    // Funcion que recupera la lista completa de todas las escuelas registradas en el sistema
    List<EntidadEscuela> obtenerTodasLasEscuelas() throws Exception;

    // Funcion que busca y devuelve el registro de una escuela especifica mediante su ID
    EntidadEscuela obtenerEscuelaPorId(Long id) throws Exception;
}