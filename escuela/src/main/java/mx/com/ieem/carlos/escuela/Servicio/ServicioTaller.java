package mx.com.ieem.carlos.escuela.Servicio;

import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadTaller;

// Interfaz que define los metodos disponibles para gestionar los talleres
public interface ServicioTaller {

    // Funcion que guarda la informacion de un nuevo taller en el sistema
    void guardarTaller(EntidadTaller entidadTaller) throws Exception;

    // Funcion que obtiene la lista de todos los talleres registrados en el sistema
    List<EntidadTaller> obtenerTodosLosTalleres() throws Exception;

    // Funcion que obtiene un taller por su ID
    EntidadTaller obtenerTallerPorId(Long id) throws Exception;
}