package es.uma.proyecto;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CLIENTE {
    
    @Id @GeneratedValue
    private String ID;
    @Column(unique=true, nullable=false)
    private String Identificacion;
    @Column(nullable=false)
    private String Tipo_Cliente;
    @Column(nullable=false)
    private Boolean Estado;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Alta;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Baja;
    @Column(nullable=false)
    private String Ciudad;
    @Column(nullable=false)
    private String CodigoPostal;
    @Column(nullable=false)
    private String Pais;
    
   
    public CLIENTE(){

    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getIdentificacion() {
        return this.Identificacion;
    }

    public void setIdentificacion(String ID) {
        this.Identificacion = ID;
    }
    
    public String tipoCliente() {
        return this.Tipo_Cliente;
    }

    public void setCliente(String cliente) {
        this.Tipo_Cliente = cliente;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PERSONA_AUTORIZADA)) {
            return false;
        }
        PERSONA_AUTORIZADA persona_Autorizada = (PERSONA_AUTORIZADA) o;
        return Objects.equals(ID, persona_Autorizada.ID) && Objects.equals(Identificacion, persona_Autorizada.Identificacion) && Objects.equals(Nombre, persona_Autorizada.Nombre) && Objects.equals(Apellidos, persona_Autorizada.Apellidos) && Objects.equals(Direccion, persona_Autorizada.Direccion) && Objects.equals(Fecha_Nacimiento, persona_Autorizada.Fecha_Nacimiento) && Objects.equals(Estado, persona_Autorizada.Estado) && Objects.equals(Fecha_Inicio, persona_Autorizada.Fecha_Inicio) && Objects.equals(FechaFin, persona_Autorizada.FechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Identificacion, Nombre, Apellidos, Direccion, Fecha_Nacimiento, Estado, Fecha_Inicio, FechaFin);
    }

}
