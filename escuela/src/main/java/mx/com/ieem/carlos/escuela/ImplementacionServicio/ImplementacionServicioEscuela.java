package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadEscuela;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioEscuela;
import mx.com.ieem.carlos.escuela.Servicio.ServicioEscuela;

@Service
public class ImplementacionServicioEscuela implements ServicioEscuela {

    @Autowired
    private RepositorioEscuela escuelaRepositorio;

    @Override
    public void guardarEscuela(EntidadEscuela escuelaDto) throws Exception

    {// Funcion que registra una nueva escuela en el sistema (TOP)
        
        // Se guarda la variable obtenida en la entidad para luego guardarla en la base de datos
        EntidadEscuela entidad = new EntidadEscuela();
        entidad.setId_escuela(new Date().getTime());
        entidad.setNombre_escuela(escuelaDto.getNombre_escuela());
        entidad.setDireccion_escuela(escuelaDto.getDireccion_escuela());

        this.escuelaRepositorio.save(entidad);

    }// Funcion que registra una nueva escuela en el sistema (BOTTOM)

    @Override
    public List<EntidadEscuela> obtenerTodasLasEscuelas() throws Exception

    {// Funcion que obtiene la lista de todas las escuelas registradas (TOP)

        // Lista completa de escuelas traida desde la base de datos
        List<EntidadEscuela> listaEscuelas = this.escuelaRepositorio.findAll();

        return listaEscuelas;

    }// Funcion que obtiene la lista de todas las escuelas registradas (BOTTOM)

    @Override
    public EntidadEscuela obtenerEscuelaPorId(Long id) throws Exception

    {// Funcion que busca una escuela en especifico por su ID (TOP)

        // Escuela encontrada por su ID, null si no existe
        EntidadEscuela escuela = this.escuelaRepositorio.findById(id).orElse(null);

        return escuela;

    }// Funcion que busca una escuela en especifico por su ID (BOTTOM)
}