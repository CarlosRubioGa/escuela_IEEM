package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadEscuela;
import mx.com.ieem.carlos.escuela.Parametros.ParametroEscuela;
import mx.com.ieem.carlos.escuela.Servicio.ServicioEscuela;

@Controller
@RequestMapping("/escuela")
public class ControladorEscuela {

    @Autowired
    private ServicioEscuela servicioEscuela;

    @RequestMapping(value = "/registro_escuela", method = RequestMethod.POST)
    public ModelAndView mostrar_registro_escuela()

    {// Funcion que muestra el formulario de la escuela (TOP)

        // Vista del formulario de registro de escuela
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("parametroEscuela", new ParametroEscuela());
        modelAndView.setViewName("registro_escuela");

        return modelAndView;

    }// Funcion que muestra el formulario de la escuela (BOTTOM)

    @RequestMapping(value = "/guardar_escuela", method = RequestMethod.POST)
    public ModelAndView guardarEscuela(@ModelAttribute ParametroEscuela parametro) throws Exception

    {// Funcion que guarda la escuela (TOP)

        // Entidad escuela construida con los valores del parametro
        EntidadEscuela dto = new EntidadEscuela();
        dto.setNombre_escuela(parametro.getTxtnombre_escuela());
        dto.setDireccion_escuela(parametro.getTxtdireccion_escuela());

        this.servicioEscuela.guardarEscuela(dto);

        // Redireccion a la vista del catalogo tras guardar
        ModelAndView modelAndView = null;
        modelAndView= new ModelAndView();
        modelAndView.setViewName("forward:/escuela/vista_escuela");

        return modelAndView;

    }// Funcion que guarda la escuela (BOTTOM)

    @RequestMapping(value = "/vista_escuela", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView vistaEscuela()

    {// Funcion que recarga la vista del catalogo de la escuela (TOP)

        // Vista del formulario recargada vacia para seguir registrando
        ModelAndView modelAndView = null;
        modelAndView= new ModelAndView();
        modelAndView.addObject("parametroEscuela", new ParametroEscuela());
        modelAndView.setViewName("registro_escuela");

        return modelAndView;

    }// Funcion que recarga la vista del catalogo de la escuela (BOTTOM)

    @RequestMapping(value = "/lista_escuela", method = RequestMethod.POST)
    public ModelAndView listaEscuela() throws Exception

    {// Funcion que muestra la lista de las escuelas (TOP)

        // Lista completa de escuelas registradas en el sistema
        ArrayList<EntidadEscuela> listaEscuelas = new ArrayList<>(this.servicioEscuela.obtenerTodasLasEscuelas());

        // Vista de la tabla de escuelas
        ModelAndView modelAndView = null;
        modelAndView= new ModelAndView();
        modelAndView.addObject("lista_escuelas", listaEscuelas);
        modelAndView.setViewName("lista_escuelas");

        return modelAndView;

    }// Funcion que muestra la lista de las escuelas (BOTTOM)
}