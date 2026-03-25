package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadAlumno;
import mx.com.ieem.carlos.escuela.Entidad.EntidadDatosPersonales;
import mx.com.ieem.carlos.escuela.Parametros.ParametroAlumno;
import mx.com.ieem.carlos.escuela.Servicio.ServicioAlumno;
import mx.com.ieem.carlos.escuela.Servicio.ServicioDatosPersonales;

@Controller
@RequestMapping("/alumno")
public class ControladorAlumno {

    @Autowired
    private ServicioAlumno servicioAlumno;

    @Autowired
    private ServicioDatosPersonales servicioDatosPersonales;

    @RequestMapping(value = "/registro_alumno", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView mostrar_registro_alumno() throws Exception

    {// Funcion que muestra el formulario para registrar un alumno (TOP)

        ModelAndView modelAndView = null;
        ArrayList <EntidadDatosPersonales> listaDatos = null;

        // Lista de datos personales disponibles para asociar al alumno
        listaDatos = new ArrayList<>(servicioDatosPersonales.obtenerTodosLosDatosPersonales());

        // Vista del formulario de registro
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_datos_personales", listaDatos);
        modelAndView.addObject("parametroAlumno", new ParametroAlumno());
        modelAndView.setViewName("registro_alumno");

        return modelAndView;

    }// Funcion que muestra el formulario para registrar un alumno (BOTTOM)

    @RequestMapping(value = "/guardar_alumno", method = RequestMethod.POST)
    public ModelAndView guardarAlumno(@ModelAttribute ParametroAlumno parametro) throws Exception

    {// Funcion que recibe los datos y guarda el registro del alumno (TOP)

        // Datos personales relacionados al alumno obtenidos por el ID del parametro
        EntidadDatosPersonales datosPersonalesRelacionados = servicioDatosPersonales.obtenerDatosPersonalesPorId(parametro.getId_datos_personales());

        // Entidad alumno construida con los datos del parametro
        EntidadAlumno entidadAlumno = new EntidadAlumno();
        entidadAlumno.setMatricula(parametro.getMatricula());
        entidadAlumno.setDatosPersonales(datosPersonalesRelacionados);

        this.servicioAlumno.guardarAlumno(entidadAlumno);

        ModelAndView modelAndView = null;

        // Redireccion a la vista del catalogo tras guardar
        modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/alumno/vista_alumnos");

        return modelAndView;

    }// Funcion que recibe los datos y guarda el registro del alumno (BOTTOM)

    @RequestMapping(value = "/vista_alumnos", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView vistaAlumnos() throws Exception

    {// Funcion que recarga la vista del catalogo despues de guardar (TOP)

        // Lista de datos personales para recargar el select del formulario
        ArrayList<EntidadDatosPersonales> listaDatos = new ArrayList<>(servicioDatosPersonales.obtenerTodosLosDatosPersonales());

        // Vista del formulario recargada con la lista actualizada
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lista_datos_personales", listaDatos);
        modelAndView.addObject("parametroAlumno", new ParametroAlumno());
        modelAndView.setViewName("registro_alumno");

        return modelAndView;

    }// Funcion que recarga la vista del catalogo despues de guardar (BOTTOM)

    @RequestMapping(value = "/lista_alumnos", method = RequestMethod.POST)
    public ModelAndView listaAlumnos() throws Exception

    {// Funcion que muestra la lista de todos los alumnos (TOP)

        // Lista completa de alumnos registrados en el sistema
        ArrayList<EntidadAlumno> listaAlumnos = new ArrayList<>(servicioAlumno.obtenerTodosLosAlumnos());

        // Vista de la tabla de alumnos
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lista_alumnos", listaAlumnos);
        modelAndView.setViewName("lista_alumnos");

        return modelAndView;

    }// Funcion que muestra la lista de todos los alumnos (BOTTOM)
}