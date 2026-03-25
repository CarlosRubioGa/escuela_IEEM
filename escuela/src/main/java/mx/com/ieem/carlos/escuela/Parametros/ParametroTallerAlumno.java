package mx.com.ieem.carlos.escuela.Parametros;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que recibe los parametros del formulario de asignacion de multiples talleres a un alumno
public class ParametroTallerAlumno {

    // ID del alumno seleccionado en el formulario
    private Long id_alumno;

    // Lista de IDs de talleres seleccionados mediante checkboxes en el formulario
    private List<Long> ids_taller = new ArrayList<>();
}