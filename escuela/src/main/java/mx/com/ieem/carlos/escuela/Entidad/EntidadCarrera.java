package mx.com.ieem.carlos.escuela.Entidad;

import java.util.Objects;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "tcarrera")
// Entidad que representa la tabla tcarrera en la base de datos
public class EntidadCarrera {

    // Llave compuesta formada por id_carrera e id_escuela
    @EmbeddedId
    private LlaveCompuestaEntidadCarrera llaveCompuestaCarrera;
    private String nombre_carrera;

    // Relacion muchos a uno con la escuela a la que pertenece la carrera
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_escuela")
    @JoinColumn(name = "id_escuela", nullable = false)
    private EntidadEscuela escuela;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.llaveCompuestaCarrera);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadCarrera other = (EntidadCarrera) obj;
        return Objects.equals(this.llaveCompuestaCarrera, other.llaveCompuestaCarrera);
    }
}