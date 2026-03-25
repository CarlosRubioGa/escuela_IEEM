package mx.com.ieem.carlos.escuela.Parametros;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que agrupa los parametros necesarios para actualizar la relacion entre un maestro y un curso
public class ParametroActualizarMaestroCurso {

    private Long idMaestro;
    private Long idCursoViejo;
    private Long idCursoNuevo;
}