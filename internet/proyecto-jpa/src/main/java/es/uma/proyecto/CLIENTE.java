package es.uma.proyecto;

import java.util.Objects;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tipo_Cliente", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("CLIENTE")
@Table(name="CLIENTE")
public class CLIENTE {
    
    @Id @GeneratedValue
    private String ID;
    @Column(unique=true, nullable=false)
    private String Identificacion;
    @Column(nullable=false)
    private String Tipo_Cliente;
    @Column(nullable=false)
    private Boolean Estado;
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Date Fecha_Alta;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Baja;
    @Column(nullable=false)
    private String Direccion;
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

    public void setIdentificacion(String ident) {
        this.Identificacion = ident;
    }
    
    public String getTipoCliente() {
        return this.Tipo_Cliente;
    }

    public void setCliente(String cliente) {
        this.Tipo_Cliente = cliente;
    }

    public Boolean isEstado() {
        return this.Estado;
    }

    public Boolean getEstado() {
        return this.Estado;
    }
    
    public String getDireccion() {
        return this.Direccion;
    }

    public void setDireccion(String dir) {
        this.Direccion = dir;
    }

    public String getCiudad() {
        return this.Ciudad;
    }

    public void setCiudad(String ciudad) {
        this.Ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return this.CodigoPostal;
    }

    public void setCodigoPostal(String cp) {
        this.CodigoPostal = cp;
    }

    public String getPais() {
        return this.Pais;
    }

    public void setPais(String pais) {
        this.Pais = pais;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CLIENTE)) return false;
        CLIENTE cliente = (CLIENTE) o;
        return Objects.equals(getID(), cliente.getID()) && Objects.equals(getIdentificacion(), cliente.Identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }
}
