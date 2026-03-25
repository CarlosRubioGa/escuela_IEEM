package mx.com.ieem.carlos.escuela.Servicio;
import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadDivision;


public interface ServicioDivision{
    
    // Funcion que guarda la informacion de una nueva division en el sistema
    void guardarDivision(EntidadDivision entidadDivision) throws Exception;

    // Funcion que obtiene la lista de todas las divisiones registradas en el sistema
    List<EntidadDivision> obtenerTodasLasDivisiones() throws Exception;

    // Funcion que obtiene una division por su ID
    EntidadDivision obtenerDivisionPorId(Long id) throws Exception;
}