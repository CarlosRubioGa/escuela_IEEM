package mx.com.ieem.carlos.escuela.Servicio;

import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadCarrera;
import mx.com.ieem.carlos.escuela.Entidad.LlaveCompuestaEntidadCarrera;


// Interfaz que define los metodos disponibles para gestionar las carreras
public interface ServicioCarrera {
    
    // Función que guarda la información de una carrera en el sistema
    void guardarCarrera(EntidadCarrera carrera) throws Exception;
    
    // Funcion que recupera la lista completa de todas las carreras registradas
    List<EntidadCarrera> obtenerTodasLasCarreras() throws Exception;

    // Función que obtiene una carrera por su ID
    EntidadCarrera obtenerCarreraPorId(LlaveCompuestaEntidadCarrera id) throws Exception;
}