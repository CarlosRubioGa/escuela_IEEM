package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadDatosPersonales;
import mx.com.ieem.carlos.escuela.Entidad.EntidadMaestro;
import mx.com.ieem.carlos.escuela.Parametros.ParametroMaestro;
import mx.com.ieem.carlos.escuela.Servicio.ServicioDatosPersonales;
import mx.com.ieem.carlos.escuela.Servicio.ServicioMaestro;

@Controller
@RequestMapping("/maestro")
public class ControladorMaestro {

    @Autowired
    private ServicioMaestro servicioMaestro;

    @Autowired
    private ServicioDatosPersonales servicioDatosPersonales;

    @RequestMapping(value = "/registro_maestro", method = RequestMethod.POST)
    public ModelAndView mostrar_registro_maestro() throws Exception

    {// Funcion que muestra el formulario para registrar un maestro (TOP)

        // Lista de datos personales disponibles para asociar al maestro
        ArrayList<EntidadDatosPersonales> listaDatos = null;
        listaDatos = new ArrayList<>(servicioDatosPersonales.obtenerTodosLosDatosPersonales());

        // Vista del formulario de registro
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_datos_personales", listaDatos);
        modelAndView.addObject("parametroMaestro", new ParametroMaestro());
        modelAndView.setViewName("registro_maestro");

        return modelAndView;

    }// Funcion que muestra el formulario para registrar un maestro (BOTTOM)

    @RequestMapping(value = "/guardar_maestro", method = RequestMethod.POST)
    public ModelAndView guardarMaestro(@ModelAttribute ParametroMaestro parametro) throws Exception

    {// Funcion que recibe los datos y guarda el registro del maestro (TOP)

        // Datos personales relacionados al maestro obtenidos por el ID del parametro
        EntidadDatosPersonales datosPersonalesRelacionados = servicioDatosPersonales.obtenerDatosPersonalesPorId(parametro.getId_datos_personales());

        // Entidad maestro construida con los datos del parametro
        EntidadMaestro entidadMaestro = new EntidadMaestro();
        entidadMaestro.setEspecialidad(parametro.getEspecialidad());
        entidadMaestro.setDatosPersonales(datosPersonalesRelacionados);

        this.servicioMaestro.guardarMaestro(entidadMaestro);

        // Redireccion a la vista del catalogo tras guardar
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/maestro/vista_maestros");

        return modelAndView;

    }// Funcion que recibe los datos y guarda el registro del maestro (BOTTOM)

    @RequestMapping(value = "/vista_maestros", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView vistaMaestros() throws Exception

    {// Funcion que recarga la vista del catalogo despues de guardar (TOP)

        // Lista de datos personales para recargar el select del formulario
        ArrayList<EntidadDatosPersonales> listaDatos = null;
        listaDatos = new ArrayList<>(servicioDatosPersonales.obtenerTodosLosDatosPersonales());

        // Vista del formulario recargada con la lista actualizada
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_datos_personales", listaDatos);
        modelAndView.addObject("parametroMaestro", new ParametroMaestro());
        modelAndView.setViewName("registro_maestro");

        return modelAndView;

    }// Funcion que recarga la vista del catalogo despues de guardar (BOTTOM)

    @RequestMapping(value = "/lista_maestros", method = RequestMethod.POST)
    public ModelAndView listaMaestros() throws Exception

    {// Funcion que muestra la lista de todos los maestros (TOP)

        // Lista completa de maestros registrados en el sistema
        ArrayList<EntidadMaestro> listaMaestros = null;
        listaMaestros = new ArrayList<>(servicioMaestro.obtenerTodosLosMaestros());

        // Vista de la tabla de maestros
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lista_maestros", listaMaestros);
        modelAndView.setViewName("lista_maestros");

        return modelAndView;

    }// Funcion que muestra la lista de todos los maestros (BOTTOM)
}