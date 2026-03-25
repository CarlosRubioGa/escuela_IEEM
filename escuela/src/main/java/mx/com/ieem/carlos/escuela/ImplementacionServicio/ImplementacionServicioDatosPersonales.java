package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.ieem.carlos.escuela.Servicio.ServicioDatosPersonales;
import mx.com.ieem.carlos.escuela.Entidad.EntidadDatosPersonales;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioDatosPersonales;

@Service
public class ImplementacionServicioDatosPersonales implements ServicioDatosPersonales {

    @Autowired
    private RepositorioDatosPersonales datosPersonalesRepositorio;

    @Override
    public void guardarDatosPersonales(EntidadDatosPersonales datosPersonalesDto) throws Exception {
        // funcion que guarda los datos personales de un usuario en la Base de Datos (TOP)

        EntidadDatosPersonales entidad = new EntidadDatosPersonales();


        entidad.setId_datos_personales(new Date().getTime());
        entidad.setNombre_persona(datosPersonalesDto.getNombre_persona());
        entidad.setApellido_persona(datosPersonalesDto.getApellido_persona());
        entidad.setCurp(datosPersonalesDto.getCurp());
        entidad.setEdad(datosPersonalesDto.getEdad());
        entidad.setGrupo_sanguineo(datosPersonalesDto.getGrupo_sanguineo());

        this.datosPersonalesRepositorio.save(entidad);

        // funcion que guarda los datos personales de un usuario en la Base de Datos (BOTTOM)
    }

    @Override
    // funcion que obtiene todos los registros de datos personales
    public List<EntidadDatosPersonales> obtenerTodosLosDatosPersonales() throws Exception {

        return this.datosPersonalesRepositorio.findAll();
    }

    @Override
    // funcion que busca unos datos personales en especifico por su ID
    public EntidadDatosPersonales obtenerDatosPersonalesPorId(Long id) throws Exception {

        return this.datosPersonalesRepositorio.findById(id).orElse(null);

    }
}