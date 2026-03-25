package mx.com.ieem.carlos.escuela.Controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mx.com.ieem.carlos.escuela.Entidad.EntidadAlumno;
import mx.com.ieem.carlos.escuela.Entidad.EntidadCarrera;
import mx.com.ieem.carlos.escuela.Entidad.EntidadMaestro;
import mx.com.ieem.carlos.escuela.Entidad.EntidadMateria;
import mx.com.ieem.carlos.escuela.Entidad.EntidadSistemasEscolares;
import mx.com.ieem.carlos.escuela.Entidad.LlaveCompuestaEntidadCarrera;
import mx.com.ieem.carlos.escuela.Parametros.ParametroActualizarSistemaEscolar;
import mx.com.ieem.carlos.escuela.Parametros.ParametroSistemaEscolar;
import mx.com.ieem.carlos.escuela.Servicio.ServicioAlumno;
import mx.com.ieem.carlos.escuela.Servicio.ServicioCarrera;
import mx.com.ieem.carlos.escuela.Servicio.ServicioMaestro;
import mx.com.ieem.carlos.escuela.Servicio.ServicioMateria;
import mx.com.ieem.carlos.escuela.Servicio.ServicioSistemasEscolares;

@Controller
@RequestMapping("/sistema_escolar")
public class ControladorSistemasEscolares {

    @Autowired
    private ServicioSistemasEscolares servicioSistemaEscolar;

    @Autowired
    private ServicioAlumno servicioAlumno;

    @Autowired
    private ServicioMaestro servicioMaestro;

    @Autowired
    private ServicioMateria servicioMateria;

    @Autowired
    private ServicioCarrera servicioCarrera;

    @RequestMapping(value = "/registro_sistema_escolar", method = RequestMethod.POST)
    public ModelAndView mostrarregistro_sistema_escolar() throws Exception
    {// Funcion que muestra el formulario de sistema escolar (TOP)

        return this.cargarVistaSistemaEscolar();

    }// Funcion que muestra el formulario de sistema escolar (BOTTOM)

    @RequestMapping(value = "/vista_sistema_escolar", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView vistaSistemaEscolarPost() throws Exception
    {// Funcion que recarga la vista de sistema escolar (TOP)

        return this.cargarVistaSistemaEscolar();

    }// Funcion que recarga la vista de sistema escolar (BOTTOM)

    private ModelAndView cargarVistaSistemaEscolar() throws Exception
    {// Funcion que carga la vista y llena las listas para los selects (TOP)

        // Lista de alumnos disponibles para el select
        ArrayList<EntidadAlumno> listaAlumnos = null;
        listaAlumnos = new ArrayList<>(this.servicioAlumno.obtenerTodosLosAlumnos());

        // Lista de maestros disponibles para el select
        ArrayList<EntidadMaestro> listaMaestros = null;
        listaMaestros = new ArrayList<>(this.servicioMaestro.obtenerTodosLosMaestros());

        // Lista de materias disponibles para el select
        ArrayList<EntidadMateria> listaMaterias = null;
        listaMaterias = new ArrayList<>(this.servicioMateria.obtenerTodasLasMaterias());

        // Lista de carreras disponibles para el select
        ArrayList<EntidadCarrera> listaCarreras = null;
        listaCarreras = new ArrayList<>(this.servicioCarrera.obtenerTodasLasCarreras());

        // Vista del formulario con todas las listas cargadas
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_alumnos", listaAlumnos);
        modelAndView.addObject("lista_maestros", listaMaestros);
        modelAndView.addObject("lista_materias", listaMaterias);
        modelAndView.addObject("lista_carreras", listaCarreras);
        modelAndView.addObject("parametroSistemaEscolar", new ParametroSistemaEscolar());
        modelAndView.setViewName("registro_sistema_escolar");

        return modelAndView;

    }// Funcion que carga la vista y llena las listas para los selects (BOTTOM)

    @RequestMapping(value = "/guardar_sistema_escolar", method = RequestMethod.POST)
    public ModelAndView guardarSistemaEscolar(@ModelAttribute ParametroSistemaEscolar parametro) throws Exception
    {// Funcion que guarda el sistema escolar (TOP)

        // Alumno obtenido por su ID 
        EntidadAlumno alumno = this.servicioAlumno.obtenerAlumnoPorId(parametro.getId_alumno());

        // Maestro obtenido por su ID 
        EntidadMaestro maestro = this.servicioMaestro.obtenerMaestroPorId(parametro.getId_maestro());

        // Llave compuesta de la carrera con
        LlaveCompuestaEntidadCarrera llaveCarrera = new LlaveCompuestaEntidadCarrera();
        llaveCarrera.setId_carrera(parametro.getId_carrera());
        llaveCarrera.setId_escuela(parametro.getId_escuela()); // ← CORRECCIÓN

        // Carrera obtenida por su llave compuesta completa
        EntidadCarrera carrera = this.servicioCarrera.obtenerCarreraPorId(llaveCarrera);

        // Materia obtenida por su ID 
        EntidadMateria materia = this.servicioMateria.obtenerMateriaPorId(parametro.getId_materia());

        // Entidad sistema escolar construida con todas las relaciones y calificacion
        EntidadSistemasEscolares entidadSistemaEscolar = new EntidadSistemasEscolares();
        entidadSistemaEscolar.setAlumno(alumno);
        entidadSistemaEscolar.setMaestro(maestro);
        entidadSistemaEscolar.setCarrera(carrera);
        entidadSistemaEscolar.setMateria(materia);
        entidadSistemaEscolar.setCalificacion(parametro.getCalificacion());

        this.servicioSistemaEscolar.guardarSistemaEscolar(entidadSistemaEscolar);

        // Redireccion a la vista del formulario para seguir registrando
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/sistema_escolar/vista_sistema_escolar");

        return modelAndView;

    }// Funcion que guarda el sistema escolar y regresa a la vista para seguir registrando (BOTTOM)

    @RequestMapping(value = "/lista_sistemas_escolares", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listaSistemasEscolares() throws Exception
    {// Funcion que muestra la lista de todos los sistemas escolares (TOP)

        // Lista completa de registros de sistemas escolares
        ArrayList<EntidadSistemasEscolares> listaSistemasEscolares =
                new ArrayList<>(this.servicioSistemaEscolar.obtenerTodosElSistemaEscolar());

        // Vista de la tabla de sistemas escolares
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("lista_sistemas_escolares", listaSistemasEscolares);
        modelAndView.setViewName("lista_sistemas_escolares");

        return modelAndView;

    }// Funcion que muestra la lista de todos los sistemas escolares (BOTTOM)

    @RequestMapping(value = "/eliminar_sistema_escolar", method = RequestMethod.POST)
    public ModelAndView eliminarSistemaEscolar(@RequestParam("id_sistema_escolar") Long idSistemaEscolar) throws Exception
    {// Funcion que elimina un registro de sistema escolar y recarga la lista (TOP)

        this.servicioSistemaEscolar.eliminarSistemaEscolar(idSistemaEscolar);

        // Redireccion a la lista actualizada tras eliminar
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/sistema_escolar/lista_sistemas_escolares");

        return modelAndView;

    }// Funcion que elimina un registro de sistema escolar y recarga la lista (BOTTOM)

    @RequestMapping(value = "/editar_sistema_escolar", method = RequestMethod.POST)
    public ModelAndView editarSistemaEscolar(@RequestParam("id_sistema_escolar") Long idSistemaEscolar) throws Exception
    {// Funcion que carga el formulario de edicion con los datos actuales del registro (TOP)

        // Registro completo del sistema escolar obtenido por su ID
        EntidadSistemasEscolares sistemaEscolar = this.servicioSistemaEscolar.obtenerSistemaEscolarPorId(idSistemaEscolar);

        // Lista de carreras disponibles 
        ArrayList<EntidadCarrera> listaCarreras = null;
        listaCarreras = new ArrayList<>(this.servicioCarrera.obtenerTodasLasCarreras());

        // Lista de materias disponibles
        ArrayList<EntidadMateria> listaMaterias = null;
        listaMaterias = new ArrayList<>(this.servicioMateria.obtenerTodasLasMaterias());

        // Vista del formulario de edicion con los datos actuales preseleccionados
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.addObject("sistema_escolar", sistemaEscolar);
        modelAndView.addObject("lista_carreras", listaCarreras);
        modelAndView.addObject("lista_materias", listaMaterias);
        modelAndView.addObject("parametroActualizar", new ParametroActualizarSistemaEscolar());
        modelAndView.setViewName("editar_sistema_escolar");

        return modelAndView;

    }// Funcion que carga el formulario de edicion con los datos actuales del registro (BOTTOM)

    @RequestMapping(value = "/actualizar_sistema_escolar", method = RequestMethod.POST)
    public ModelAndView actualizarSistemaEscolar(@ModelAttribute ParametroActualizarSistemaEscolar parametro) throws Exception
    {// Funcion que guarda los cambios de carrera, materia y calificacion (TOP)

        // Registro completo recuperado para conservar alumno y maestro sin modificarlos
        EntidadSistemasEscolares sistemaEscolar = this.servicioSistemaEscolar.obtenerSistemaEscolarPorId(parametro.getId_sistema_escolar());

        // Llave compuesta de la nueva carrera con ID de carrera y escuela
        LlaveCompuestaEntidadCarrera llaveCarrera = new LlaveCompuestaEntidadCarrera();
        llaveCarrera.setId_carrera(parametro.getId_carrera());
        llaveCarrera.setId_escuela(parametro.getId_escuela()); 

        // Nueva carrera obtenida por su llave compuesta completa
        EntidadCarrera carrera = this.servicioCarrera.obtenerCarreraPorId(llaveCarrera);

        // Nueva materia obtenida por su ID 
        EntidadMateria materia = this.servicioMateria.obtenerMateriaPorId(parametro.getId_materia());

        // Se actualizan unicamente los campos modificables
        sistemaEscolar.setCarrera(carrera);
        sistemaEscolar.setMateria(materia);
        sistemaEscolar.setCalificacion(parametro.getCalificacion());

        this.servicioSistemaEscolar.actualizarSistemaEscolar(sistemaEscolar);

        // Redireccion a la lista actualizada tras guardar cambios
        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/sistema_escolar/lista_sistemas_escolares");

        return modelAndView;

    }// Funcion que guarda los cambios de carrera, materia y calificacion (BOTTOM)
}