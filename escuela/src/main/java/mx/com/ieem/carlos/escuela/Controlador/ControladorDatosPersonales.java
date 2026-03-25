package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadDatosPersonales;
import mx.com.ieem.carlos.escuela.Parametros.ParametroDatosPersonales;
import mx.com.ieem.carlos.escuela.Servicio.ServicioDatosPersonales;

@Controller
@RequestMapping("/datos_personales")
public class ControladorDatosPersonales {

    @Autowired
    private ServicioDatosPersonales servicioDatosPersonales;

    @RequestMapping(value = "/registro_datos_personales", method = RequestMethod.POST)
    public ModelAndView mostrarRegistro_datos_personales()

    {// Funcion que muestra el formulario de datos personales (TOP)

        // Vista del formulario de registro de datos personales
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("parametroDatosPersonales", new ParametroDatosPersonales());
        modelAndView.setViewName("registro_datos_personales");

        return modelAndView;

    }// Funcion que muestra el formulario de datos personales (BOTTOM)

    @RequestMapping(value = "/guardar_datos_personales", method = RequestMethod.POST)
    public ModelAndView guardarDatosPersonales(@ModelAttribute ParametroDatosPersonales parametro) throws Exception

    {// Funcion que guarda el registro de datos personales (TOP)

        // Entidad de datos personales construida con los valores del parametro
        EntidadDatosPersonales entidadDatosPersonales = new EntidadDatosPersonales();
        entidadDatosPersonales.setCurp(parametro.getTxtcurp());
        entidadDatosPersonales.setNombre_persona(parametro.getTxtnombre_persona());
        entidadDatosPersonales.setApellido_persona(parametro.getTxtapellido_persona());
        entidadDatosPersonales.setEdad(parametro.getTxtedad());
        entidadDatosPersonales.setGrupo_sanguineo(parametro.getTxtgruposanguineo());

        this.servicioDatosPersonales.guardarDatosPersonales(entidadDatosPersonales);

        // Redireccion a la vista del catalogo tras guardar
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/datos_personales/vista_datos_personales");

        return modelAndView;

    }// Funcion que guarda el registro de datos personales (BOTTOM)

    @RequestMapping(value = "/vista_datos_personales", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView vistaDatosPersonales()

    {// Funcion que recarga la vista del catalogo de datos personales (TOP)

        // Vista del formulario recargada vacia para seguir registrando
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("parametroDatosPersonales", new ParametroDatosPersonales());
        modelAndView.setViewName("registro_datos_personales");

        return modelAndView;

    }// Funcion que recarga la vista del registro de datos personales (BOTTOM)

    @RequestMapping(value = "/lista_datos_personales", method = RequestMethod.POST)
    public ModelAndView lista_datos_personales() throws Exception

    {// Funcion que muestra la lista de todos los datos personales (TOP)

        // Lista completa de datos personales registrados en el sistema
        ArrayList<EntidadDatosPersonales> listaDatosPersonales = new ArrayList<>(
                this.servicioDatosPersonales.obtenerTodosLosDatosPersonales()
        );

        // Vista de la tabla de datos personales
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_datos_personales", listaDatosPersonales);
        modelAndView.setViewName("lista_datos_personales");

        return modelAndView;

    }// Funcion que muestra la lista de todos los datos personales (BOTTOM)
}