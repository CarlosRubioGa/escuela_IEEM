package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadDivision;
import mx.com.ieem.carlos.escuela.Parametros.ParametroDivision;
import mx.com.ieem.carlos.escuela.Servicio.ServicioDivision;

@Controller
@RequestMapping("/division")
public class ControladorDivision {

    @Autowired
    private ServicioDivision servicioDivision;

    @RequestMapping(value = "/registro_division", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView mostrarRegistroDivision()

    {// Funcion que muestra el formulario de division (TOP)

        // Vista del formulario de registro de division
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("parametroDivision", new ParametroDivision());
        modelAndView.setViewName("registro_division");

        return modelAndView;

    }// Funcion que muestra el formulario de division (BOTTOM)

    @RequestMapping(value = "/guardar_division", method = RequestMethod.POST)
    public ModelAndView guardarDivision(@ModelAttribute ParametroDivision parametro) throws Exception

    {// Funcion que guarda la division (TOP)

        // Entidad division construida con los valores del parametro
        EntidadDivision entidadDivision = new EntidadDivision();
        entidadDivision.setNombre_division(parametro.getNombreDivision());

        this.servicioDivision.guardarDivision(entidadDivision);

        // Redireccion al formulario para seguir registrando
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/division/registro_division");

        return modelAndView;

    }// Funcion que guarda la division (BOTTOM)

    @RequestMapping(value = "/lista_divisiones", method = RequestMethod.POST)
    public ModelAndView listaDivisiones() throws Exception 
    {// Funcion que muestra la lista de divisiones (TOP)
        // Lista completa de divisiones registradas en el sistema
        ArrayList<EntidadDivision> lista = new ArrayList<>(this.servicioDivision.obtenerTodasLasDivisiones());

        // Vista de la lista de divisiones;
        ModelAndView modelAndView = new ModelAndView("lista_divisiones");
        
        // Se agrega la lista de divisiones a la vista para mostrarla en la tabla.
        modelAndView.addObject("lista_divisiones", lista); 

        return modelAndView;
    }
}