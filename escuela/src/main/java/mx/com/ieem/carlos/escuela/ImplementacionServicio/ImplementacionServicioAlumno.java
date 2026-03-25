package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadAlumno;

import mx.com.ieem.carlos.escuela.Repositorio.RepositorioAlumno;
import mx.com.ieem.carlos.escuela.Servicio.ServicioAlumno;

@Service
public class ImplementacionServicioAlumno implements ServicioAlumno {

    @Autowired
    private RepositorioAlumno alumnoRepositorio;

    @Override
    public void guardarAlumno(EntidadAlumno alumno) throws Exception

    {// Funcion que guarda un nuevo alumno en la base de datos (TOP)

        // Genera un ID unico para el nuevo alumno usando la marca de tiempo actual
        alumno.setId_alumno(new Date().getTime());

        this.alumnoRepositorio.save(alumno);

    }// Funcion que guarda un nuevo alumno en la base de datos (BOTTOM)

    @Override
    public List<EntidadAlumno> obtenerTodosLosAlumnos() throws Exception

    {// Funcion que obtiene la lista de todos los alumnos registrados (TOP)

        // Lista completa de alumnos traida desde la base de datos
        List<EntidadAlumno> listaAlumnos = this.alumnoRepositorio.findAll();

        return listaAlumnos;

    }// Funcion que obtiene la lista de todos los alumnos registrados (BOTTOM)

    @Override
    public EntidadAlumno obtenerAlumnoPorId(Long id) throws Exception

    {// Funcion que busca un alumno en especifico por su llave compuesta (TOP)

        // Alumno encontrado por su llave compuesta, null si no existe
        EntidadAlumno id_Alumno = new EntidadAlumno();
        id_Alumno.setId_alumno(id);
        EntidadAlumno alumno = this.alumnoRepositorio.findById(id_Alumno.getId_alumno()).orElse(null);

        return alumno;

    }// Funcion que busca un alumno en especifico por su llave compuesta (BOTTOM)
}