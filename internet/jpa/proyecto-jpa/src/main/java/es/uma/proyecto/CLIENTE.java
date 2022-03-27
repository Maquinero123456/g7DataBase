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
        if (o == this)
            return true;
        if (!(o instanceof CLIENTE)) {
            return false;
        }
        CLIENTE cliente = (CLIENTE) o;
        return Objects.equals(ID, cliente.ID) && Objects.equals(Identificacion, cliente.Identificacion) && Objects.equals(Tipo_Cliente, cliente.Tipo_Cliente) && Objects.equals(Estado, cliente.Estado) && Objects.equals(Fecha_Alta, cliente.Fecha_Alta) && Objects.equals(Fecha_Baja, cliente.Fecha_Baja) && Objects.equals(Direccion, cliente.Direccion) && Objects.equals(Ciudad, cliente.Ciudad) && Objects.equals(CodigoPostal, cliente.CodigoPostal) && Objects.equals(Pais, cliente.Pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Identificacion, Tipo_Cliente, Estado, Fecha_Alta, Fecha_Baja, Direccion, Ciudad, CodigoPostal, Pais);
    }

}
