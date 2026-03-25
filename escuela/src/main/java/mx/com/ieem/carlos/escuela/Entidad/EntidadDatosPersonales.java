package mx.com.ieem.carlos.escuela.Entidad;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "tdatos_personales")
// Entidad que representa la tabla tdatos_personales en la base de datos
public class EntidadDatosPersonales {

    @Id
    private Long id_datos_personales;
    private String curp;
    private String nombre_persona;
    private String apellido_persona;
    private int edad;
    private String grupo_sanguineo;

    // Relacion inversa con el alumno que usa estos datos personales
    @OneToOne(mappedBy = "datosPersonales", fetch = FetchType.LAZY)
    private EntidadAlumno alumno;

    // Relacion inversa con el maestro que usa estos datos personales
    @OneToOne(mappedBy = "datosPersonales", fetch = FetchType.LAZY)
    private EntidadMaestro maestro;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id_datos_personales);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final EntidadDatosPersonales other = (EntidadDatosPersonales) obj;
        return Objects.equals(this.id_datos_personales, other.id_datos_personales);
    }
}