package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadCurso;
import mx.com.ieem.carlos.escuela.Entidad.EntidadDivision;
import mx.com.ieem.carlos.escuela.Parametros.ParametroCurso;
import mx.com.ieem.carlos.escuela.Servicio.ServicioCurso;
import mx.com.ieem.carlos.escuela.Servicio.ServicioDivision;

@Controller
@RequestMapping("/curso")
public class ControladorCurso {

    @Autowired
    private ServicioCurso servicioCurso;

    @Autowired
    private ServicioDivision servicioDivision;

    @RequestMapping(value = "/registro_curso", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView mostrarRegistroCurso() throws Exception

    {// Funcion que muestra el formulario de registro de curso (TOP)

        ModelAndView modelAndView = null;
        ArrayList<EntidadDivision> listaDivisiones = null;

        // Lista de divisiones disponibles para asociar al curso
        listaDivisiones = new ArrayList<>(this.servicioDivision.obtenerTodasLasDivisiones());

        // Vista del formulario de registro de curso con la lista de divisiones cargada
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_divisiones", listaDivisiones);
        modelAndView.addObject("parametroCurso", new ParametroCurso());
        modelAndView.setViewName("registro_curso");

        return modelAndView;

    }// Funcion que muestra el formulario de registro de curso (BOTTOM)

    @RequestMapping(value = "/guardar_curso", method = RequestMethod.POST)
    public ModelAndView guardarCurso(@ModelAttribute ParametroCurso parametro) throws Exception

    {// Funcion que guarda el curso (TOP)

        // Division relacionada obtenida por el ID del parametro
        EntidadDivision divisionRelacionada = this.servicioDivision.obtenerDivisionPorId(parametro.getId_division());

        // Entidad curso construida con los valores del parametro y la division relacionada
        EntidadCurso entidadCurso = new EntidadCurso();
        entidadCurso.setNombre_curso(parametro.getNombreCurso());
        entidadCurso.setDivision(divisionRelacionada);

        this.servicioCurso.guardarCurso(entidadCurso);

        // Redireccion al formulario para seguir registrando
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/curso/registro_curso");

        return modelAndView;

    }// Funcion que guarda el curso (BOTTOM)

    @RequestMapping(value = "/lista_cursos", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listaCursos() throws Exception

    {// Funcion que muestra la lista de cursos (TOP)

        ModelAndView modelAndView = null;
        ArrayList<EntidadCurso> listaCursos = null;

        // Lista completa de cursos registrados en el sistema
        listaCursos = new ArrayList<>(this.servicioCurso.obtenerTodosLosCursos());

        // Vista de la tabla de cursos
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_cursos", listaCursos);
        modelAndView.setViewName("lista_cursos");

        return modelAndView;

    }// Funcion que muestra la lista de cursos (BOTTOM)
}