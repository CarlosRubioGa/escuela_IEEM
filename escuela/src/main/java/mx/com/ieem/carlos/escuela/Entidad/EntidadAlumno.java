package mx.com.ieem.carlos.escuela.Entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "talumno")
// Entidad que representa la tabla talumno en la base de datos
public class EntidadAlumno {

    @Id
    private Long id_alumno;
    private String matricula;

    // Relacion uno a uno con los datos personales del alumno
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_datos_personales", nullable = false, unique = true)
    private EntidadDatosPersonales datosPersonales;

    // Relacion muchos a muchos con los talleres en los que esta inscrito el alumno
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<EntidadTallerAlumno> tallerAlumno = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id_alumno);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadAlumno other = (EntidadAlumno) obj;
        return Objects.equals(this.id_alumno, other.id_alumno);
    }
}