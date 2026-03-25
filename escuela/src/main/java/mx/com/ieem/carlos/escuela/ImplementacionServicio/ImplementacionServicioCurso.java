package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadCurso;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioCurso;
import mx.com.ieem.carlos.escuela.Servicio.ServicioCurso;

@Service
public class ImplementacionServicioCurso implements ServicioCurso {

    @Autowired
    private RepositorioCurso cursoRepositorio;

    @Override
    public void guardarCurso(EntidadCurso entidadCurso) throws Exception

    {// Funcion que registra un nuevo curso en el sistema (TOP)

        // ID generado con timestamp para garantizar que sea unico
        entidadCurso.setId_curso(new Date().getTime());

        this.cursoRepositorio.save(entidadCurso);

    }// Funcion que registra un nuevo curso en el sistema (BOTTOM)

    @Override
    public List<EntidadCurso> obtenerTodosLosCursos() throws Exception

    {// Funcion que obtiene la lista de todos los cursos registrados (TOP)

        // Lista completa de cursos traida desde la base de datos
        List<EntidadCurso> listaCursos = this.cursoRepositorio.findAll();

        return listaCursos;

    }// Funcion que obtiene la lista de todos los cursos registrados (BOTTOM)

    @Override
    public EntidadCurso obtenerCursoPorId(Long id) throws Exception

    {// Funcion que busca un curso en especifico por su ID (TOP)

        // Curso encontrado por su ID, null si no existe
        EntidadCurso curso = this.cursoRepositorio.findById(id).orElse(null);

        return curso;

    }// Funcion que busca un curso en especifico por su ID (BOTTOM)
}