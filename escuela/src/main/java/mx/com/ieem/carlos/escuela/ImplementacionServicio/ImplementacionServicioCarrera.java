package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadCarrera;
import mx.com.ieem.carlos.escuela.Entidad.LlaveCompuestaEntidadCarrera;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioCarrera;
import mx.com.ieem.carlos.escuela.Servicio.ServicioCarrera;

@Service
public class ImplementacionServicioCarrera implements ServicioCarrera {

    @Autowired
    private RepositorioCarrera carreraRepositorio;

    @Override
    public void guardarCarrera(EntidadCarrera carrera) throws Exception

    {// Funcion que guarda una nueva carrera en la base de datos (TOP)

        // Funcion que genera el id unico para la carrera usando un timestamp
        LlaveCompuestaEntidadCarrera llaveCompuestaEntidadCarrera = new LlaveCompuestaEntidadCarrera();
        llaveCompuestaEntidadCarrera.setId_carrera(new Date().getTime());

        // Se asigna la llave compuesta a la carrera antes de guardarla
        carrera.setLlaveCompuestaCarrera(llaveCompuestaEntidadCarrera);

        this.carreraRepositorio.save(carrera);

    }// Funcion que guarda una nueva carrera en la base de datos (BOTTOM)

    @Override
    public List<EntidadCarrera> obtenerTodasLasCarreras() throws Exception

    {// Funcion que obtiene la lista de todas las carreras registradas (TOP)

        // Lista completa de carreras traida desde la base de datos
        List<EntidadCarrera> listaCarreras = this.carreraRepositorio.findAll();

        return listaCarreras;

    }// Funcion que obtiene la lista de todas las carreras registradas (BOTTOM)

    @Override
    public EntidadCarrera obtenerCarreraPorId(LlaveCompuestaEntidadCarrera id) throws Exception

    {// Funcion que busca una carrera en especifico por su llave compuesta (TOP)

        // Carrera encontrada por su llave compuesta, null si no existe
        EntidadCarrera carrera = this.carreraRepositorio.findById(id).orElse(null);

        return carrera;

    }// Funcion que busca una carrera en especifico por su llave compuesta (BOTTOM)
}