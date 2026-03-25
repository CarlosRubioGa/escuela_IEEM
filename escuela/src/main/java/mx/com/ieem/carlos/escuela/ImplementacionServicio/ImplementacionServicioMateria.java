package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadMateria;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioMateria;
import mx.com.ieem.carlos.escuela.Servicio.ServicioMateria;

@Service
public class ImplementacionServicioMateria implements ServicioMateria {

    @Autowired
    private RepositorioMateria materiaRepositorio;

    @Override
    public void guardarMateria(EntidadMateria entidadMateria) throws Exception
    {// Funcion que registra una nueva materia en el sistema (TOP)

        // Se construye la entidad y se guarda mediante JPA save()
        EntidadMateria entidad = new EntidadMateria();
        entidad.setId_materia(new Date().getTime());
        entidad.setNombre_materia(entidadMateria.getNombre_materia());
        entidad.setCreditos_materia(entidadMateria.getCreditos_materia());

        this.materiaRepositorio.save(entidad);

    }// Funcion que registra una nueva materia en el sistema (BOTTOM)

    @Override
    public List<EntidadMateria> obtenerTodasLasMaterias() throws Exception
    {// Funcion que obtiene la lista de todas las materias registradas (TOP)

        // Lista completa de materias traida desde la base de datos via JPQL
        List<EntidadMateria> listaMaterias = this.materiaRepositorio.findAllMaterias();

        return listaMaterias;

    }// Funcion que obtiene la lista de todas las materias registradas (BOTTOM)

    @Override
    public EntidadMateria obtenerMateriaPorId(Long id) throws Exception
    {// Funcion que busca una materia en especifico por su ID (TOP)

        // Materia encontrada por su ID via JPQL, null si no existe
        EntidadMateria materia = this.materiaRepositorio.findMateriaById(id).orElse(null);

        return materia;

    }// Funcion que busca una materia en especifico por su ID (BOTTOM)
}