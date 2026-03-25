package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadAlumno;
import mx.com.ieem.carlos.escuela.Entidad.EntidadTaller;
import mx.com.ieem.carlos.escuela.Entidad.EntidadTallerAlumno;
import mx.com.ieem.carlos.escuela.Entidad.LlaveCompuestaEntidadTallerAlumno;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioAlumno;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioTaller;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioTallerAlumno;
import mx.com.ieem.carlos.escuela.Servicio.ServicioTallerAlumno;

@Service
public class ImplementacionServicioTallerAlumno implements ServicioTallerAlumno {

    @Autowired
    private RepositorioTallerAlumno repositorioTallerAlumno;

    @Autowired
    private RepositorioAlumno repositorioAlumno;

    @Autowired
    private RepositorioTaller repositorioTaller;

    @Override
    public void asignarTallerAlumno(Long idTaller, Long idAlumno) throws Exception

    {// Funcion que asigna un alumno a un taller si no esta ya inscrito (TOP)

        // Construir la llave compuesta con los IDs recibidos
        LlaveCompuestaEntidadTallerAlumno llave = new LlaveCompuestaEntidadTallerAlumno();
        llave.setId_taller(idTaller);
        llave.setId_alumno(idAlumno);

        // Si ya existe esa combinacion taller-alumno, no hacer nada
        boolean yaAsignado = this.repositorioTallerAlumno.existsById(llave);
        if (yaAsignado) return;

        // Verifica que el taller existe
        EntidadTaller taller = this.repositorioTaller.findById(idTaller)
                .orElseThrow(() -> new Exception("Taller no encontrado: " + idTaller));

        // Verifica que el alumno existe
        EntidadAlumno alumno = this.repositorioAlumno.findById(idAlumno)
                .orElseThrow(() -> new Exception("Alumno no encontrado: " + idAlumno));

        // Crear y guardar la nueva relacion taller-alumno
        EntidadTallerAlumno tallerAlumno = new EntidadTallerAlumno();
        tallerAlumno.setLlaveCompuestaEntidadTallerAlumno(llave);
        tallerAlumno.setTaller(taller);
        tallerAlumno.setAlumno(alumno);

        this.repositorioTallerAlumno.save(tallerAlumno);

    }// Funcion que asigna un alumno a un taller si no esta ya inscrito (BOTTOM)

    @Override
    public void eliminarTallerAlumno(Long idTaller, Long idAlumno) throws Exception

    {// Funcion que elimina la relacion entre un taller y un alumno (TOP)

        // Construir la llave compuesta con los IDs recibidos
        LlaveCompuestaEntidadTallerAlumno llave = new LlaveCompuestaEntidadTallerAlumno();
        llave.setId_taller(idTaller);
        llave.setId_alumno(idAlumno);

        // Eliminar el registro de la relacion taller-alumno por su llave compuesta
        this.repositorioTallerAlumno.deleteById(llave);

    }// Funcion que elimina la relacion entre un taller y un alumno (BOTTOM)
}