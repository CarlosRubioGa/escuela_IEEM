package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadDivision;
import mx.com.ieem.carlos.escuela.Entidad.EntidadTaller;
import mx.com.ieem.carlos.escuela.Parametros.ParametroTaller;
import mx.com.ieem.carlos.escuela.Servicio.ServicioDivision;
import mx.com.ieem.carlos.escuela.Servicio.ServicioTaller;

@Controller
@RequestMapping("/taller")
public class ControladorTaller {

    @Autowired
    private ServicioTaller servicioTaller;

    @Autowired
    private ServicioDivision servicioDivision;

    @RequestMapping(value = "/registro_taller", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView mostrarRegistroTaller() throws Exception

    {// Funcion que muestra el formulario de registro de taller (TOP)

        // Lista de divisiones disponibles para asociar al taller
        ArrayList<EntidadDivision> listaDivisiones = null;
        listaDivisiones = new ArrayList<>(this.servicioDivision.obtenerTodasLasDivisiones());

        // Vista del formulario de registro de taller con la lista de divisiones cargada
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_divisiones", listaDivisiones);
        modelAndView.addObject("parametroTaller", new ParametroTaller());
        modelAndView.setViewName("registro_taller");

        return modelAndView;

    }// Funcion que muestra el formulario de registro de taller (BOTTOM)

    @RequestMapping(value = "/guardar_taller", method = RequestMethod.POST)
    public ModelAndView guardarTaller(@ModelAttribute ParametroTaller parametro) throws Exception

    {// Funcion que guarda el taller (TOP)

        // Division relacionada obtenida por el ID del parametro
        EntidadDivision divisionRelacionada = this.servicioDivision.obtenerDivisionPorId(parametro.getId_division());

        // Entidad taller construida con los valores del parametro y la division relacionada
        EntidadTaller entidadTaller = new EntidadTaller();
        entidadTaller.setNombre_taller(parametro.getNombreTaller());
        entidadTaller.setDivision(divisionRelacionada);

        this.servicioTaller.guardarTaller(entidadTaller);

        // Redireccion al formulario para seguir registrando
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/taller/registro_taller");

        return modelAndView;

    }// Funcion que guarda el taller (BOTTOM)

    @RequestMapping(value = "/lista_talleres", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listaTalleres() throws Exception

    {// Funcion que muestra la lista de talleres (TOP)

        // Lista completa de talleres registrados en el sistema
        ArrayList<EntidadTaller> listaTalleres = null;
        listaTalleres = new ArrayList<>(this.servicioTaller.obtenerTodosLosTalleres());

        // Vista de la tabla de talleres
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_talleres", listaTalleres);
        modelAndView.setViewName("lista_talleres");

        return modelAndView;

    }// Funcion que muestra la lista de talleres (BOTTOM)
}