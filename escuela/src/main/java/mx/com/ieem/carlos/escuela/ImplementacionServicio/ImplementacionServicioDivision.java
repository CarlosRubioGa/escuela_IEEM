package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadDivision;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioDivision;
import mx.com.ieem.carlos.escuela.Servicio.ServicioDivision;

@Service
public class ImplementacionServicioDivision implements ServicioDivision {

    @Autowired
    private RepositorioDivision divisionRepositorio;

    @Override
    public void guardarDivision(EntidadDivision entidadDivision) throws Exception

    {// Funcion que registra una nueva division en el sistema (TOP)

        // Se guarda la variable obtenida en la entidad para luego guardarla en la base de datos
        EntidadDivision entidad = new EntidadDivision();
        entidad.setId_division(new Date().getTime());
        entidad.setNombre_division(entidadDivision.getNombre_division());

        this.divisionRepositorio.save(entidad);

    }// Funcion que registra una nueva division en el sistema (BOTTOM)

    @Override
    public List<EntidadDivision> obtenerTodasLasDivisiones() throws Exception

    {// Funcion que obtiene la lista de todas las divisiones registradas (TOP)

        // Lista completa de divisiones traida desde la base de datos
        List<EntidadDivision> listaDivisiones = this.divisionRepositorio.findAll();

        return listaDivisiones;

    }// Funcion que obtiene la lista de todas las divisiones registradas (BOTTOM)

    @Override
    public EntidadDivision obtenerDivisionPorId(Long id) throws Exception

    {// Funcion que busca una division en especifico por su ID (TOP)

        // Division encontrada por su ID, null si no existe
        EntidadDivision division = this.divisionRepositorio.findById(id).orElse(null);

        return division;

    }// Funcion que busca una division en especifico por su ID (BOTTOM)
}