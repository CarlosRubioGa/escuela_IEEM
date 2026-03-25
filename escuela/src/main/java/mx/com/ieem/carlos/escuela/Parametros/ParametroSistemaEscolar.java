package mx.com.ieem.carlos.escuela.Parametros;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que agrupa los parametros necesarios para registrar un sistema escolar
public class ParametroSistemaEscolar {

    private Long id_alumno;
    private Long id_maestro;
    private Long id_carrera;
    private Long id_escuela;
    private Long id_materia;
    private double calificacion;

} 