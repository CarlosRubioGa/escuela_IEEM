package mx.com.ieem.carlos.escuela.Parametros;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que recibe los parametros del formulario de asignacion de multiples cursos a un maestro
public class ParametroCursoMaestro {

    // ID del maestro seleccionado en el formulario
    private Long idMaestro;

    // Lista de IDs de cursos seleccionados mediante checkboxes en el formulario
    private List<Long> idsCurso = new ArrayList<>();
}