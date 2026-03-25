package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadMaestro;
import mx.com.ieem.carlos.escuela.Entidad.EntidadCurso;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioMaestro;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioCurso;
import mx.com.ieem.carlos.escuela.Servicio.ServicioCursoMaestro;

@Service
public class ImplementacionServicioCursoMaestro implements ServicioCursoMaestro {

    @Autowired
    private RepositorioCurso repositorioCurso;

    @Autowired
    private RepositorioMaestro repositorioMaestro;

    @Override
    public void asignarCursoMaestro(Long idCurso, Long idMaestro) throws Exception

    {// Funcion que agrega un maestro a la lista de un curso (TOP)

        //Funcion que obtiene Curso y por su ID
        EntidadCurso curso = this.repositorioCurso.findById(idCurso).orElse(null);
        EntidadMaestro maestro = this.repositorioMaestro.findById(idMaestro).orElse(null);

        // Validacion de existencia antes de asignar
        if (curso == null || maestro == null) return;

        // Funcion que asigna un maestro a la lista de maestros del curso, evitando duplicados
        if (!curso.getMaestros().contains(maestro)) {
            curso.getMaestros().add(maestro);
            this.repositorioCurso.save(curso);
        }

    }// Funcion que agrega un maestro a la lista de un curso (BOTTOM)

    @Override
    public void actualizarCursoMaestro(Long idCurso, Long idMaestro) throws Exception 
    {// Funcion que elimina un maestro de la lista de un curso (TOP)
    
    //Funcion que obtiene Curso y Maestro por su ID
    EntidadCurso curso = this.repositorioCurso.findById(idCurso).orElse(null);
    EntidadMaestro maestro = this.repositorioMaestro.findById(idMaestro).orElse(null);
    // Validacion de existencia antes de eliminar
    if (curso == null || maestro == null) return;
    
    // Funcion que elimina el maestro de la lista de maestros del curso
    curso.getMaestros().remove(maestro);
    this.repositorioCurso.save(curso);
}
}