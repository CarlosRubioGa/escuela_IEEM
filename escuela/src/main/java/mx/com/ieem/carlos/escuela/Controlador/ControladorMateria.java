package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadMateria;
import mx.com.ieem.carlos.escuela.Parametros.ParametroMateria;
import mx.com.ieem.carlos.escuela.Servicio.ServicioMateria;

@Controller
@RequestMapping("/materia")
public class ControladorMateria {

    @Autowired
    private ServicioMateria servicioMateria;

    @RequestMapping(value = "/registro_materia", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView mostrarRegistroMateria()

    {// Funcion que muestra el formulario de materia (TOP)

        // Vista del formulario de registro de materia
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("parametroMateria", new ParametroMateria());
        modelAndView.setViewName("registro_materia");

        return modelAndView;

    }// Funcion que muestra el formulario de materia (BOTTOM)

    @RequestMapping(value = "/guardar_materia", method = RequestMethod.POST)
    public ModelAndView guardarMateria(@ModelAttribute ParametroMateria parametro) throws Exception

    {// Funcion que guarda la materia (TOP)

        // Entidad materia construida con los valores del parametro
        EntidadMateria entidadMateria = new EntidadMateria();
        entidadMateria.setNombre_materia(parametro.getTxtnombremateria());
        entidadMateria.setCreditos_materia(parametro.getTxtcreditosmateria());

        this.servicioMateria.guardarMateria(entidadMateria);

        // Redireccion al formulario para seguir registrando
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/materia/registro_materia");

        return modelAndView;

    }// Funcion que guarda la materia (BOTTOM)

    @RequestMapping(value = "/lista_materias", method = RequestMethod.POST)
    public ModelAndView listaMaterias() throws Exception

    {// Funcion que muestra la lista de materias (TOP)

        // Lista completa de materias registradas en el sistema
        ArrayList<EntidadMateria> listaMaterias = null;
        listaMaterias = new ArrayList<>(this.servicioMateria.obtenerTodasLasMaterias());

        // Vista de la tabla de materias
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_materias", listaMaterias);
        modelAndView.setViewName("lista_materias");

        return modelAndView;

    }// Funcion que muestra la lista de materias (BOTTOM)
}