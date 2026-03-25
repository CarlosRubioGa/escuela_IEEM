package mx.com.ieem.carlos.escuela.Entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "tmaestro")
// Entidad que representa la tabla tmaestro en la base de datos
public class EntidadMaestro {

    @Id
    private Long id_maestro;
    private String especialidad;

    // Relacion uno a uno con los datos personales del maestro
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_datos_personales", nullable = false, unique = true)
    private EntidadDatosPersonales datosPersonales;

    // Relacion muchos a muchos con los cursos en los que esta inscrito el maestro
    @ManyToMany(mappedBy = "maestros", fetch = FetchType.LAZY)
    private List<EntidadCurso> cursos = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id_maestro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadMaestro other = (EntidadMaestro) obj;
        return Objects.equals(this.id_maestro, other.id_maestro);
    }
}