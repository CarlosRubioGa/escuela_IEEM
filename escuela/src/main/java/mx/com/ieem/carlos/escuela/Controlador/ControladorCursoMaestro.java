package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadMaestro;
import mx.com.ieem.carlos.escuela.Entidad.EntidadCurso;
import mx.com.ieem.carlos.escuela.Parametros.ParametroCursoMaestro;
import mx.com.ieem.carlos.escuela.Parametros.ParametroActualizarCursoMaestro;
import mx.com.ieem.carlos.escuela.Parametros.ParametroActualizarMaestroCurso;
import mx.com.ieem.carlos.escuela.Servicio.ServicioMaestro;
import mx.com.ieem.carlos.escuela.Servicio.ServicioCurso;
import mx.com.ieem.carlos.escuela.Servicio.ServicioCursoMaestro;

@Controller
@RequestMapping("/curso")
public class ControladorCursoMaestro {

    @Autowired
    private ServicioCursoMaestro servicioCursoMaestro;

    @Autowired
    private ServicioCurso servicioCurso;

    @Autowired
    private ServicioMaestro servicioMaestro;

    @RequestMapping(value = "/vista_curso_maestro", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView vistaCursoMaestro() throws Exception

    {// Funcion que carga la vista principal con todas las listas (TOP)

        ModelAndView modelAndView = null;
        ArrayList<EntidadCurso> listaCursos = null;
        ArrayList<EntidadMaestro> listaMaestros = null;

        // Lista de todos los cursos disponibles para los checkboxes
        listaCursos = new ArrayList<>(this.servicioCurso.obtenerTodosLosCursos());

        // Lista de todos los maestros disponibles para el select
        listaMaestros = new ArrayList<>(this.servicioMaestro.obtenerTodosLosMaestros());

        // Vista principal con listas cargadas
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_cursos", listaCursos);
        modelAndView.addObject("lista_maestros", listaMaestros);
        modelAndView.setViewName("registro_curso_maestro");

        return modelAndView;

    }// Funcion que carga la vista principal con todas las listas (BOTTOM)

    @RequestMapping(value = "/asignar_curso_maestro", method = RequestMethod.POST)
    public ModelAndView asignarCursoMaestro(
            @ModelAttribute ParametroCursoMaestro parametro) throws Exception

    {// Funcion que asigna multiples cursos seleccionados mediante checkboxes a un maestro (TOP)

        // ID del maestro seleccionado en el formulario
        Long idMaestro = parametro.getIdMaestro();

        // Se recorre la lista de IDs de cursos y se asigna cada uno al maestro seleccionado
        for (Long idCurso : parametro.getIdsCurso()) {

            // Asignacion individual de cada curso al maestro seleccionado
            this.servicioCursoMaestro.asignarCursoMaestro(idCurso, idMaestro);

        }

        // Recarga la vista principal
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/curso/vista_curso_maestro");

        return modelAndView;

    }// Funcion que asigna multiples cursos seleccionados mediante checkboxes a un maestro (BOTTOM)

    @RequestMapping(value = "/cursos_de_maestro", method = RequestMethod.POST)
    public ModelAndView cursosDeMaestro(
            @ModelAttribute ParametroCursoMaestro parametro) throws Exception

    {// Funcion que muestra los cursos asignados a un maestro especifico (TOP)

        ModelAndView modelAndView = null;
        ArrayList<EntidadCurso> cursosDelMaestro = null;
        ArrayList<EntidadMaestro> listaMaestros = null;

        // Maestro seleccionado con su lista de cursos
        EntidadMaestro maestro = this.servicioMaestro.obtenerMaestroPorId(parametro.getIdMaestro());

        // Lista de cursos del maestro
        cursosDelMaestro = new ArrayList<>(maestro.getCursos());

        // Lista de todos los maestros
        listaMaestros = new ArrayList<>(this.servicioMaestro.obtenerTodosLosMaestros());

        // Vista con los cursos del maestro seleccionado
        modelAndView = new ModelAndView();
        modelAndView.addObject("maestro_seleccionado", maestro);
        modelAndView.addObject("cursos_del_maestro", cursosDelMaestro);
        modelAndView.addObject("lista_maestros", listaMaestros);
        modelAndView.setViewName("lista_curso_maestro");

        return modelAndView;

    }// Funcion que muestra los cursos asignados a un maestro especifico (BOTTOM)

    @RequestMapping(value = "/lista_curso_maestro", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listaCursoMaestro() throws Exception

    {// Funcion que muestra todos los cursos con sus maestros (TOP)

        ArrayList<EntidadCurso> listaCursos = null;
        ModelAndView modelAndView = null;

        // Lista completa de cursos con sus maestros
        listaCursos = new ArrayList<>(this.servicioCurso.obtenerTodosLosCursos());

        // Vista de la lista de cursos con maestros
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_cursos", listaCursos);
        modelAndView.setViewName("lista_curso_maestro");

        return modelAndView;

    }// Funcion que muestra todos los cursos con sus maestros (BOTTOM)

    @RequestMapping(value = "/lista_maestro_curso", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listaMaestroCurso() throws Exception

    {// Funcion que muestra todos los maestros con sus cursos (TOP)

        ArrayList<EntidadMaestro> listaMaestros = null;
        ModelAndView modelAndView = null;

        // Lista completa de maestros con sus cursos
        listaMaestros = new ArrayList<>(this.servicioMaestro.obtenerTodosLosMaestros());

        // Vista de la lista de maestros con cursos
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_maestros", listaMaestros);
        modelAndView.setViewName("lista_maestro_curso");

        return modelAndView;

    }// Funcion que muestra todos los maestros con sus cursos (BOTTOM)

    @RequestMapping(value = "/eliminar_curso_maestro", method = RequestMethod.POST)
    public ModelAndView eliminarCursoMaestro(
            @ModelAttribute ParametroCursoMaestro parametro) throws Exception

    {// Funcion que elimina la relacion entre un curso y un maestro (TOP)

        // Desasigna el curso indicado del maestro indicado
        this.servicioCursoMaestro.actualizarCursoMaestro(parametro.getIdsCurso().get(0), parametro.getIdMaestro());

        // Regresa a la lista de cursos con maestros
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/curso/lista_curso_maestro");

        return modelAndView;

    }// Funcion que elimina la relacion entre un curso y un maestro (BOTTOM)

    @RequestMapping(value = "/editar_curso_maestro", method = RequestMethod.POST)
    public ModelAndView editarCursoMaestro(
            @ModelAttribute ParametroCursoMaestro parametro) throws Exception

    {// Funcion que carga el formulario para cambiar el maestro de un curso (TOP)

        ArrayList<EntidadMaestro> listaMaestros = null;
        ModelAndView modelAndView = null;

        // Curso actual y maestro obtenidos por su ID
        EntidadCurso curso = this.servicioCurso.obtenerCursoPorId(parametro.getIdsCurso().get(0));
        EntidadMaestro maestroActual = this.servicioMaestro.obtenerMaestroPorId(parametro.getIdMaestro());

        // Lista de todos los maestros disponibles para seleccionar
        listaMaestros = new ArrayList<>(this.servicioMaestro.obtenerTodosLosMaestros());

        // Vista del formulario de edicion con datos precargados
        modelAndView = new ModelAndView();
        modelAndView.addObject("curso", curso);
        modelAndView.addObject("maestro_actual", maestroActual);
        modelAndView.addObject("lista_maestros", listaMaestros);
        modelAndView.setViewName("editar_curso_maestro");

        return modelAndView;

    }// Funcion que carga el formulario para cambiar el maestro de un curso (BOTTOM)

    @RequestMapping(value = "/actualizar_curso_maestro", method = RequestMethod.POST)
    public ModelAndView actualizarCursoMaestro(
            @ModelAttribute ParametroActualizarCursoMaestro parametro) throws Exception

    {// Funcion que guarda el nuevo maestro asignado a un curso (TOP)

        // Quita el maestro viejo y asigna el nuevo al mismo curso
        this.servicioCursoMaestro.actualizarCursoMaestro(parametro.getIdCurso(), parametro.getIdMaestroViejo());
        this.servicioCursoMaestro.asignarCursoMaestro(parametro.getIdCurso(), parametro.getIdMaestroNuevo());

        // Regresa a la lista de cursos con maestros
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/curso/lista_curso_maestro");

        return modelAndView;

    }// Funcion que guarda el nuevo maestro asignado a un curso (BOTTOM)

    @RequestMapping(value = "/eliminar_maestro_curso", method = RequestMethod.POST)
    public ModelAndView eliminarMaestroCurso(
            @ModelAttribute ParametroCursoMaestro parametro) throws Exception

    {// Funcion que elimina la relacion entre un maestro y un curso (TOP)

        // Desasigna el curso indicado del maestro indicado
        this.servicioCursoMaestro.actualizarCursoMaestro(parametro.getIdsCurso().get(0), parametro.getIdMaestro());

        // Regresa a la lista de maestros con cursos
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/curso/lista_maestro_curso");

        return modelAndView;

    }// Funcion que elimina la relacion entre un maestro y un curso (BOTTOM)

    @RequestMapping(value = "/editar_maestro_curso", method = RequestMethod.POST)
    public ModelAndView editarMaestroCurso(
            @ModelAttribute ParametroCursoMaestro parametro) throws Exception

    {// Funcion que carga el formulario para cambiar el curso de un maestro (TOP)

        // Maestro y curso actual obtenidos por su ID
        EntidadMaestro maestro = this.servicioMaestro.obtenerMaestroPorId(parametro.getIdMaestro());
        EntidadCurso cursoActual = this.servicioCurso.obtenerCursoPorId(parametro.getIdsCurso().get(0));

        // Lista de todos los cursos disponibles para seleccionar
        ArrayList<EntidadCurso> listaCursos = new ArrayList<>(this.servicioCurso.obtenerTodosLosCursos());

        // Vista del formulario de edicion con datos precargados
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("maestro", maestro);
        modelAndView.addObject("curso_actual", cursoActual);
        modelAndView.addObject("lista_cursos", listaCursos);
        modelAndView.setViewName("editar_maestro_curso");

        return modelAndView;

    }// Funcion que carga el formulario para cambiar el curso de un maestro (BOTTOM)

    @RequestMapping(value = "/actualizar_maestro_curso", method = RequestMethod.POST)
    public ModelAndView actualizarMaestroCurso(
            @ModelAttribute ParametroActualizarMaestroCurso parametro) throws Exception

    {// Funcion que guarda el nuevo curso asignado a un maestro (TOP)

        // Quita el curso viejo y asigna el nuevo al mismo maestro
        this.servicioCursoMaestro.actualizarCursoMaestro(parametro.getIdCursoViejo(), parametro.getIdMaestro());
        this.servicioCursoMaestro.asignarCursoMaestro(parametro.getIdCursoNuevo(), parametro.getIdMaestro());

        // Regresa a la lista de maestros con cursos
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/curso/lista_maestro_curso");

        return modelAndView;

    }// Funcion que guarda el nuevo curso asignado a un maestro (BOTTOM)
}