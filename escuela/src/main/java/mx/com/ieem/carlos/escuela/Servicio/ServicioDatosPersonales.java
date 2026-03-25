package mx.com.ieem.carlos.escuela.Servicio;

import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadDatosPersonales;

// Interfaz que define los metodos disponibles para gestionar los datos personales
public interface ServicioDatosPersonales {
    
    // Funcion que guarda un nuevo registro de datos personales en el sistema
    void guardarDatosPersonales(EntidadDatosPersonales datosPersonalesDto) throws Exception;
    
    // Funcion que recupera la lista completa de todos los datos personales registrados
    List<EntidadDatosPersonales> obtenerTodosLosDatosPersonales() throws Exception;

    // Funcion que busca y devuelve un registro de datos personales especifico mediante su ID
    EntidadDatosPersonales obtenerDatosPersonalesPorId(Long id) throws Exception;
}