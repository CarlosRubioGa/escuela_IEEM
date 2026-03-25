package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadSistemasEscolares;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioSistemasEscolares;
import mx.com.ieem.carlos.escuela.Servicio.ServicioSistemasEscolares;

@Service
public class ImplementacionServicioSistemasEscolares implements ServicioSistemasEscolares {

    @Autowired
    private RepositorioSistemasEscolares repositorioSistemaEscolar;

    @Override
    public void guardarSistemaEscolar(EntidadSistemasEscolares sistemaEscolar) throws Exception

    {// Funcion que guarda un nuevo registro en sistemas escolares (TOP)

        // Se utiliza el timestamp para crear un id unico
        EntidadSistemasEscolares entidadSistemasEscolares = new EntidadSistemasEscolares();
        entidadSistemasEscolares.setId_sistema_escolar(new Date().getTime());

        // Se asigna la llave al registro antes de persistirlo
        sistemaEscolar.setId_sistema_escolar(entidadSistemasEscolares.getId_sistema_escolar());

        this.repositorioSistemaEscolar.save(sistemaEscolar);

    }// Funcion que guarda un nuevo registro en sistemas escolares (BOTTOM)

    @Override
    public List<EntidadSistemasEscolares> obtenerTodosElSistemaEscolar() throws Exception

    {// Funcion que obtiene la lista de todos los registros de sistemas escolares (TOP)

        // Lista completa de registros traida desde la base de datos
        List<EntidadSistemasEscolares> listaSistemasEscolares = this.repositorioSistemaEscolar.findAll();

        return listaSistemasEscolares;

    }// Funcion que obtiene la lista de todos los registros de sistemas escolares (BOTTOM)

    @Override
    public void eliminarSistemaEscolar(Long id) throws Exception

    {// Funcion que elimina un registro de sistemas escolares por su ID (TOP)

        this.repositorioSistemaEscolar.deleteById(id);

    }// Funcion que elimina un registro de sistemas escolares por su ID (BOTTOM)

    @Override
    public EntidadSistemasEscolares obtenerSistemaEscolarPorId(Long id) throws Exception

    {// Funcion que busca un registro de sistemas escolares por su ID (TOP)

        // Registro encontrado por su ID, null si no existe
        EntidadSistemasEscolares sistemaEscolar = this.repositorioSistemaEscolar.findById(id).orElse(null);

        return sistemaEscolar;

    }// Funcion que busca un registro de sistemas escolares por su ID (BOTTOM)

    @Override
    public void actualizarSistemaEscolar(EntidadSistemasEscolares sistemaEscolar) throws Exception

    {// Funcion que actualiza carrera, materia y calificacion de un registro existente (TOP)

        // JPA detecta la llave existente y ejecuta un UPDATE en lugar de INSERT
        this.repositorioSistemaEscolar.save(sistemaEscolar);

    }// Funcion que actualiza carrera, materia y calificacion de un registro existente (BOTTOM)
}