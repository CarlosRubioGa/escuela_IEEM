package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadCarrera;
import mx.com.ieem.carlos.escuela.Entidad.EntidadEscuela;
import mx.com.ieem.carlos.escuela.Parametros.ParametroCarrera;
import mx.com.ieem.carlos.escuela.Servicio.ServicioCarrera;
import mx.com.ieem.carlos.escuela.Servicio.ServicioEscuela;

@Controller
@RequestMapping("/carrera")
public class ControladorCarrera {

    @Autowired
    private ServicioCarrera servicioCarrera;

    @Autowired
    private ServicioEscuela servicioEscuela;

    @RequestMapping(value = "/registro_carrera", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView mostrarregistro_carrera() throws Exception

    {// Funcion que muestra el formulario de registro de carrera (TOP)

        ModelAndView modelAndView = null;
        ArrayList<EntidadEscuela> listaEscuelas = null;

        // Lista de escuelas disponibles para asociar a la carrera
        listaEscuelas = new ArrayList<>(this.servicioEscuela.obtenerTodasLasEscuelas());

        // Vista del formulario de registro
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_escuelas", listaEscuelas);
        modelAndView.addObject("parametroCarrera", new ParametroCarrera());
        modelAndView.setViewName("registro_carrera");

        return modelAndView;

    }// Funcion que muestra el formulario de registro de carrera (BOTTOM)

    @RequestMapping(value = "/guardar_carrera", method = RequestMethod.POST)
    public ModelAndView guardarCarrera(@ModelAttribute ParametroCarrera parametro) throws Exception

    {// Funcion que procesa el registro de la carrera (TOP)


        // Escuela relacionada obtenida por el ID del parametro
        EntidadEscuela escuelaRelacionada = this.servicioEscuela.obtenerEscuelaPorId(parametro.getId_escuela());

        // Entidad carrera construida con los valores del parametro
        EntidadCarrera entidadCarrera = new EntidadCarrera();
        entidadCarrera.setNombre_carrera(parametro.getNombre_carrera());
        entidadCarrera.setEscuela(escuelaRelacionada);

        this.servicioCarrera.guardarCarrera(entidadCarrera);

        // Redireccion al formulario para seguir registrando
        
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/carrera/registro_carrera");

        return modelAndView;

    }// Funcion que procesa el registro de la carrera (BOTTOM)

    @RequestMapping(value = "/lista_carreras", method = RequestMethod.POST)
    public ModelAndView listaCarreras() throws Exception

    {// Funcion que muestra la lista de todas las carreras (TOP)

        ModelAndView modelAndView = null;
        ArrayList<EntidadCarrera> listaCarreras = null;

        // Lista completa de carreras registradas en el sistema
        listaCarreras = new ArrayList<>(servicioCarrera.obtenerTodasLasCarreras());

        // Vista de la tabla de carreras
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_carreras", listaCarreras);
        modelAndView.setViewName("lista_carreras");

        return modelAndView;

    }// Funcion que muestra la lista de todas las carreras (BOTTOM)
}