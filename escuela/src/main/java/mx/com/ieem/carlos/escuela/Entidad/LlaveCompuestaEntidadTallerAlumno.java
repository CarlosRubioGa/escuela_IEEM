package mx.com.ieem.carlos.escuela.Entidad;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
// funcion que define la estructura de la llave compuesta para el taller y el alumno
public class LlaveCompuestaEntidadTallerAlumno {

    private long id_alumno;
    private long id_taller; 
}