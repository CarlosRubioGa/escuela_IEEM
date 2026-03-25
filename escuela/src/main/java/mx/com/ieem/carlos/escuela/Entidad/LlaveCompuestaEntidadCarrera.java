package mx.com.ieem.carlos.escuela.Entidad;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
// funcion que define la estructura de la llave compuesta para la carrera
public class LlaveCompuestaEntidadCarrera {

    private long id_carrera;
    private long id_escuela; 
}