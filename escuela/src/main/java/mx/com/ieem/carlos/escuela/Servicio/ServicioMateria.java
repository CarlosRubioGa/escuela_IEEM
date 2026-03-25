package mx.com.ieem.carlos.escuela.Servicio;
import java.util.List;

import mx.com.ieem.carlos.escuela.Entidad.EntidadMateria;


public interface ServicioMateria{
    
    // Funcion que guarda la informacion de una nueva materia en el sistema
    void guardarMateria(EntidadMateria materiaDto) throws Exception;

    // Funcion que obtiene la lista de todas las materias registradas en el sistema
    List<EntidadMateria> obtenerTodasLasMaterias() throws Exception;

    // Funcion que obtiene una materia por su ID
    EntidadMateria obtenerMateriaPorId(Long id) throws Exception;
}