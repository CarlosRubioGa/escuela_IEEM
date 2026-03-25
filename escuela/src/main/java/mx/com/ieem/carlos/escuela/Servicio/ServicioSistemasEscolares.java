package mx.com.ieem.carlos.escuela.Servicio;

import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadSistemasEscolares;

public interface ServicioSistemasEscolares {

    // Funcion que guarda un registro de sistemas escolares
    void guardarSistemaEscolar(EntidadSistemasEscolares sistemaEscolar) throws Exception;

    // Funcion que obtiene todos los registros de sistemas escolares
    List<EntidadSistemasEscolares> obtenerTodosElSistemaEscolar() throws Exception;

    // Funcion que elimina un registro de sistemas escolares por su ID
    void eliminarSistemaEscolar(Long id) throws Exception;

    // Funcion que busca un registro de sistemas escolares por su ID
    EntidadSistemasEscolares obtenerSistemaEscolarPorId(Long id) throws Exception;

    // Funcion que actualiza carrera, materia y calificacion de un registro existente
    void actualizarSistemaEscolar(EntidadSistemasEscolares sistemaEscolar) throws Exception;
    
}