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
@Table(schema = "carlos", name = "ttaller_alumno")
public class EntidadTallerAlumno {

    @EmbeddedId
    private LlaveCompuestaEntidadTallerAlumno llaveCompuestaEntidadTallerAlumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_taller")
    @JoinColumn(name = "id_taller", nullable = false, insertable = false, updatable = false)
    private EntidadTaller taller;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_alumno")
    @JoinColumn(name = "id_alumno", nullable = false, insertable = false, updatable = false)
    private EntidadAlumno alumno;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.llaveCompuestaEntidadTallerAlumno);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadTallerAlumno other = (EntidadTallerAlumno) obj;
        return Objects.equals(this.llaveCompuestaEntidadTallerAlumno, other.llaveCompuestaEntidadTallerAlumno);
    }
}