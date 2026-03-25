package mx.com.ieem.carlos.escuela.Parametros;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que agrupa los parametros necesarios para registrar datos personales
public class ParametroDatosPersonales {

    private String txtcurp;
    private String txtnombre_persona;
    private String txtapellido_persona;
    private int txtedad;
    private String txtgruposanguineo;

}