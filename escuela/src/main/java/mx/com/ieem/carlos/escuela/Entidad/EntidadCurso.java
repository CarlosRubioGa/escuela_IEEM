package mx.com.ieem.carlos.escuela.Entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "tcurso")
// Entidad que representa la tabla tcurso en la base de datos
public class EntidadCurso {

    @Id
    private Long id_curso;
    private String nombre_curso;

    // Division a la que pertenece este curso
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_division", nullable = false)
    private EntidadDivision division;

    // Relacion muchos a muchos con los maestros asignados al curso
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        schema = "carlos",
        name = "tcurso_maestro",
        joinColumns = @JoinColumn(name = "id_curso"),
        inverseJoinColumns = @JoinColumn(name = "id_maestro")
    )
    private List<EntidadMaestro> maestros = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id_curso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadCurso other = (EntidadCurso) obj;
        return Objects.equals(this.id_curso, other.id_curso);
    }
}