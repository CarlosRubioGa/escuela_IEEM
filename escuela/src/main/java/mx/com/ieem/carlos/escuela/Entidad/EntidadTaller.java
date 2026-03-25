package mx.com.ieem.carlos.escuela.Entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "ttaller")
// Entidad que representa la tabla ttaller en la base de datos
public class EntidadTaller {

    @Id
    private Long id_taller;
    private String nombre_taller;

    // Division a la que pertenece este taller
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_division", nullable = false)
    private EntidadDivision division;

    // Lista de relaciones taller-alumno asociadas a este taller
    @OneToMany(mappedBy = "taller", fetch = FetchType.LAZY)
    private List<EntidadTallerAlumno> tallerAlumno = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id_taller);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadTaller other = (EntidadTaller) obj;
        return Objects.equals(this.id_taller, other.id_taller);
    }
}