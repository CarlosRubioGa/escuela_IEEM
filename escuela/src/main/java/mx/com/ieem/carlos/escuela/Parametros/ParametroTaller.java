package mx.com.ieem.carlos.escuela.Parametros;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que recibe los parametros del formulario de registro de taller
public class ParametroTaller {
    
    private String nombreTaller;

    private Long id_division;
}