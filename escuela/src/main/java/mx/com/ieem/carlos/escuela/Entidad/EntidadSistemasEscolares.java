package mx.com.ieem.carlos.escuela.Entidad;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "tsistemas_escolares")
// Entidad que representa la tabla tsistemas_escolares en la base de datos
public class EntidadSistemasEscolares {

    @Id
    private Long id_sistema_escolar;

    // Alumno al que pertenece este registro escolar
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alumno", nullable = false)
    private EntidadAlumno alumno;

    // Maestro que imparte la materia en este registro
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_maestro", nullable = false)
    private EntidadMaestro maestro;

    // Carrera a la que pertenece este registro
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera"),
        @JoinColumn(name = "id_escuela", referencedColumnName = "id_escuela")
    })
    private EntidadCarrera carrera;

    // Materia cursada en este registro escolar
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_materia", nullable = false)
    
    private EntidadMateria materia;
    private double calificacion;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id_sistema_escolar);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadSistemasEscolares other = (EntidadSistemasEscolares) obj;
        return Objects.equals(this.id_sistema_escolar, other.id_sistema_escolar);
    }
}