package mx.com.ieem.carlos.escuela.Parametros;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Clase que recibe los parametros del formulario de registro de curso
public class ParametroCurso {

    // Nombre del curso capturado desde el formulario
    private String nombreCurso;

    // ID de la division seleccionada en el formulario
    private Long id_division;
}