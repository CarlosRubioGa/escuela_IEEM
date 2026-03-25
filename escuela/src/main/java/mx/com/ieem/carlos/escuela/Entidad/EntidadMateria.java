package mx.com.ieem.carlos.escuela.Entidad;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "carlos", name = "tmateria")
// Entidad que representa la tabla tmateria en la base de datos
public class EntidadMateria 

    {
    @Id     
    private Long id_materia; 
    private String nombre_materia; 
    private int creditos_materia; 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.id_materia ^ (this.id_materia >>> 32));
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
        final EntidadMateria other = (EntidadMateria) obj;
        return Objects.equals(this.id_materia, other.id_materia);
    }
}