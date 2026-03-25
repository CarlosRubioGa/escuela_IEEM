package mx.com.ieem.carlos.escuela.Entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "tdivision")
// Entidad que representa la tabla tdivision en la base de datos
public class EntidadDivision {

    @Id
    private Long id_division;
    private String nombre_division;

    // Lista de talleres que pertenecen a esta division
    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntidadTaller> talleres = new ArrayList<>();

    // Lista de cursos que pertenecen a esta division
    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntidadCurso> cursos = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.id_division ^ (this.id_division >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntidadDivision other = (EntidadDivision) obj;
        return Objects.equals(this.id_division, other.id_division);
    }
}