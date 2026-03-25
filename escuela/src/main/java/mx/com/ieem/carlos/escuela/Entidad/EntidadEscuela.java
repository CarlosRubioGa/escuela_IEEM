package mx.com.ieem.carlos.escuela.Entidad;

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
@Table(schema = "carlos", name = "tescuela")
// Entidad que representa la tabla tescuela en la base de datos
public class EntidadEscuela {

    @Id
    private Long id_escuela;
    private String nombre_escuela;
    private String direccion_escuela;

    // Lista de carreras que pertenecen a esta escuela
    @OneToMany(mappedBy = "escuela", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntidadCarrera> carreras;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id_escuela);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadEscuela other = (EntidadEscuela) obj;
        return Objects.equals(this.id_escuela, other.id_escuela);
    }
}