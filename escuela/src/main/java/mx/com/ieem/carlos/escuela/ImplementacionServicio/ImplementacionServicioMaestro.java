package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadMaestro;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioMaestro;
import mx.com.ieem.carlos.escuela.Servicio.ServicioMaestro;

@Service
public class ImplementacionServicioMaestro implements ServicioMaestro {

    @Autowired
    private RepositorioMaestro maestroRepositorio;

    @Override
    public void guardarMaestro(EntidadMaestro maestro) throws Exception

    {// Funcion que guarda un nuevo maestro en la base de datos (TOP)

        // funcion que guarda la Id generada con timestamp 
        Long id_maestro = new Date().getTime();
        maestro.setId_maestro(id_maestro);

        // Se asigna la llave al maestro antes de persistirlo
        maestro.setId_maestro(id_maestro);

        this.maestroRepositorio.save(maestro);

    }// Funcion que guarda un nuevo maestro en la base de datos (BOTTOM)

    @Override
    public List<EntidadMaestro> obtenerTodosLosMaestros() throws Exception

    {// Funcion que obtiene la lista de todos los maestros registrados (TOP)

        // Lista completa de maestros traida desde la base de datos
        List<EntidadMaestro> listaMaestros = this.maestroRepositorio.findAll();

        return listaMaestros;

    }// Funcion que obtiene la lista de todos los maestros registrados (BOTTOM)

    @Override
    public EntidadMaestro obtenerMaestroPorId(Long id) throws Exception

    {// Funcion que busca un maestro en especifico por su ID (TOP)

        // Maestro encontrado por su ID, null si no existe
        EntidadMaestro maestro = this.maestroRepositorio.findById(id).orElse(null);

        return maestro;

    }// Funcion que busca un maestro en especifico por su ID (BOTTOM)
}