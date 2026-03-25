package mx.com.ieem.carlos.escuela.ImplementacionServicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.ieem.carlos.escuela.Entidad.EntidadTaller;
import mx.com.ieem.carlos.escuela.Repositorio.RepositorioTaller;
import mx.com.ieem.carlos.escuela.Servicio.ServicioTaller;

@Service
public class ImplementacionServicioTaller implements ServicioTaller {

    @Autowired
    private RepositorioTaller tallerRepositorio;

    @Override
    public void guardarTaller(EntidadTaller entidadTaller) throws Exception

    {// Funcion que registra un nuevo taller en el sistema (TOP)

        // ID generado con timestamp para garantizar que sea unico
        entidadTaller.setId_taller(new Date().getTime());

        this.tallerRepositorio.save(entidadTaller);

    }// Funcion que registra un nuevo taller en el sistema (BOTTOM)

    @Override
    public List<EntidadTaller> obtenerTodosLosTalleres() throws Exception

    {// Funcion que obtiene la lista de todos los talleres registrados (TOP)

        // Lista completa de talleres traida desde la base de datos
        List<EntidadTaller> listaTalleres = this.tallerRepositorio.findAll();

        return listaTalleres;

    }// Funcion que obtiene la lista de todos los talleres registrados (BOTTOM)

    @Override
    public EntidadTaller obtenerTallerPorId(Long id) throws Exception

    {// Funcion que busca un taller en especifico por su ID (TOP)

        // Taller encontrado por su ID, null si no existe
        EntidadTaller taller = this.tallerRepositorio.findById(id).orElse(null);

        return taller;

    }// Funcion que busca un taller en especifico por su ID (BOTTOM)
}