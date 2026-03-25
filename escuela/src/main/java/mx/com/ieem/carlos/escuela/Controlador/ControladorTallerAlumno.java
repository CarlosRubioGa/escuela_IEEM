package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadAlumno;
import mx.com.ieem.carlos.escuela.Entidad.EntidadTaller;
import mx.com.ieem.carlos.escuela.Entidad.EntidadTallerAlumno;
import mx.com.ieem.carlos.escuela.Parametros.ParametroTallerAlumno;
import mx.com.ieem.carlos.escuela.Servicio.ServicioAlumno;
import mx.com.ieem.carlos.escuela.Servicio.ServicioTaller;
import mx.com.ieem.carlos.escuela.Servicio.ServicioTallerAlumno;

@Controller
@RequestMapping("/taller")
public class ControladorTallerAlumno {

    @Autowired
    private ServicioTallerAlumno servicioTallerAlumno;

    @Autowired
    private ServicioTaller servicioTaller;

    @Autowired
    private ServicioAlumno servicioAlumno;

    @RequestMapping(value = "/vista_taller_alumno", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView vistaTallerAlumno() throws Exception

    {// Funcion que carga la vista principal con todas las listas (TOP)

        // Lista de todos los talleres disponibles para los checkboxes
        ArrayList<EntidadTaller> listaTalleres = null;
         listaTalleres = new ArrayList<>(this.servicioTaller.obtenerTodosLosTalleres());

        // Lista de todos los alumnos disponibles para el select
        ArrayList<EntidadAlumno> listaAlumnos = null;
        listaAlumnos = new ArrayList<>(this.servicioAlumno.obtenerTodosLosAlumnos());

        // Parametro vacio para el formulario de asignacion multiple
        ParametroTallerAlumno parametroTallerAlumno = new ParametroTallerAlumno();

        // Vista principal con listas cargadas
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_talleres", listaTalleres);
        modelAndView.addObject("lista_alumnos", listaAlumnos);
        modelAndView.addObject("parametroTallerAlumno", parametroTallerAlumno);
        modelAndView.setViewName("registro_taller_alumno");

        return modelAndView;

    }// Funcion que carga la vista principal con todas las listas (BOTTOM)

    @RequestMapping(value = "/asignar_taller", method = RequestMethod.POST)
    public ModelAndView asignarTaller(
            @ModelAttribute ParametroTallerAlumno parametroTallerAlumno) throws Exception

    {// Funcion que asigna multiples talleres seleccionados mediante checkboxes a un alumno (TOP)

        // ID del alumno seleccionado en el formulario
        Long idAlumno = parametroTallerAlumno.getId_alumno();

        // Se recorre la lista de IDs de talleres y se asigna cada uno al alumno seleccionado
        for (Long idTaller : parametroTallerAlumno.getIds_taller()) {

            // Asignacion individual de cada taller al alumno seleccionado
            this.servicioTallerAlumno.asignarTallerAlumno(idTaller, idAlumno);

        }

        // Recarga la vista principal
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/taller/vista_taller_alumno");

        return modelAndView;

    }// Funcion que asigna multiples talleres seleccionados mediante checkboxes a un alumno (BOTTOM)

    @RequestMapping(value = "/eliminar_taller_alumno", method = RequestMethod.POST)
    public ModelAndView eliminarTallerAlumno(
            @RequestParam("id_taller") Long idTaller,
            @RequestParam("id_alumno") Long idAlumno) throws Exception

    {// Funcion que elimina la relacion entre un taller y un alumno (TOP)

        // Elimina la relacion taller-alumno usando los IDs recibidos
        this.servicioTallerAlumno.eliminarTallerAlumno(idTaller, idAlumno);

        // Regresa a la lista de talleres con alumnos
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/taller/lista_taller_alumno");

        return modelAndView;

    }// Funcion que elimina la relacion entre un taller y un alumno (BOTTOM)

    @RequestMapping(value = "/talleres_de_alumno", method = RequestMethod.POST)
    public ModelAndView talleresDe(@RequestParam("id_alumno") Long idAlumno) throws Exception

    {// Funcion que muestra los talleres asignados a un alumno especifico (TOP)

        // Alumno seleccionado con su lista de tallerAlumnos
        EntidadAlumno alumno = this.servicioAlumno.obtenerAlumnoPorId(idAlumno);

        // Lista de EntidadTallerAlumno del alumno
        ArrayList<EntidadTallerAlumno> talleresDelAlumno = null;
        talleresDelAlumno = new ArrayList<>(alumno.getTallerAlumno());

        // Lista de todos los alumnos
        ArrayList<EntidadAlumno> listaAlumnos = null;
        listaAlumnos = new ArrayList<>(this.servicioAlumno.obtenerTodosLosAlumnos());

        // Vista con los talleres del alumno seleccionado
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("alumno_seleccionado", alumno);
        modelAndView.addObject("talleres_del_alumno", talleresDelAlumno);
        modelAndView.addObject("lista_alumnos", listaAlumnos);
        modelAndView.setViewName("lista_alumno_taller");

        return modelAndView;

    }// Funcion que muestra los talleres asignados a un alumno especifico (BOTTOM)

    @RequestMapping(value = "/alumnos_de_taller", method = RequestMethod.POST)
    public ModelAndView alumnosDe(@RequestParam("id_taller") Long idTaller) throws Exception

    {// Funcion que muestra los alumnos inscritos en un taller especifico (TOP)

        // Taller seleccionado con su lista de tallerAlumno
        EntidadTaller taller = this.servicioTaller.obtenerTallerPorId(idTaller);

        // Lista de EntidadTallerAlumno del taller
        ArrayList<EntidadTallerAlumno> alumnosDelTaller = null;
        alumnosDelTaller = new ArrayList<>(taller.getTallerAlumno());

        // Lista de todos los talleres
        ArrayList<EntidadTaller> listaTalleres = null;
        listaTalleres = new ArrayList<>(this.servicioTaller.obtenerTodosLosTalleres());

        // Vista con los alumnos del taller seleccionado
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("taller_seleccionado", taller);
        modelAndView.addObject("alumnos_del_taller", alumnosDelTaller);
        modelAndView.addObject("lista_talleres", listaTalleres);
        modelAndView.setViewName("lista_taller_alumno");

        return modelAndView;

    }// Funcion que muestra los alumnos inscritos en un taller especifico (BOTTOM)

    @RequestMapping(value = "/lista_taller_alumno", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listaTallerAlumno() throws Exception

    {// Funcion que muestra todos los talleres con sus alumnos (TOP)

        // Lista completa de talleres con sus alumnos
        ArrayList<EntidadTaller> listaTalleres = null;
        listaTalleres = new ArrayList<>(this.servicioTaller.obtenerTodosLosTalleres());

        // Vista de la lista de talleres con alumnos
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_talleres", listaTalleres);
        modelAndView.setViewName("lista_taller_alumno");

        return modelAndView;

    }// Funcion que muestra todos los talleres con sus alumnos (BOTTOM)

    @RequestMapping(value = "/lista_alumno_taller", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listaAlumnoTaller() throws Exception

    {// Funcion que muestra todos los alumnos con sus talleres (TOP)

        // Lista completa de alumnos con sus talleres
        ArrayList<EntidadAlumno> listaAlumnos = null;
        listaAlumnos = new ArrayList<>(this.servicioAlumno.obtenerTodosLosAlumnos());

        // Lista completa de talleres
        ArrayList<EntidadTaller> listaTalleres = null;
        listaTalleres = new ArrayList<>(this.servicioTaller.obtenerTodosLosTalleres());

        // Vista de la lista de alumnos con talleres
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_alumnos", listaAlumnos);
        modelAndView.addObject("lista_talleres", listaTalleres);
        modelAndView.setViewName("lista_alumno_taller");

        return modelAndView;

    }// Funcion que muestra todos los alumnos con sus talleres (BOTTOM)
}