package mx.com.ieem.carlos.escuela.Parametros;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que agrupa los parametros necesarios para actualizar un sistema escolar
public class ParametroActualizarSistemaEscolar {

    private Long id_sistema_escolar;
    private Long id_carrera;
    private Long id_escuela; 
    private Long id_materia;
    private double calificacion;
}