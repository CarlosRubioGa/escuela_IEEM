package mx.com.ieem.carlos.escuela.Parametros;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que agrupa los parametros necesarios para actualizar la relacion entre un curso y un maestro
public class ParametroActualizarCursoMaestro {

    private Long idCurso;
    private Long idMaestroViejo;
    private Long idMaestroNuevo;
}