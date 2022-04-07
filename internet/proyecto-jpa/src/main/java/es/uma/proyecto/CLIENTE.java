package es.uma.proyecto;

import java.util.Objects;
import javax.persistence.*;
import java.util.Date;

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
    private String Estado;
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
    
    public CLIENTE(String id, String ident, String tp, String est, Date alta, String direc, String ciudad, String cp, String pais){
    	this.ID = id;
    	this.Identificacion = ident;
    	this.Tipo_Cliente = tp;
    	this.Estado = est;
    	this.Fecha_Alta = alta;
    	this.Direccion = direc;
    	this.Ciudad = ciudad;
    	this.CodigoPostal = cp;
    	this.Pais = pais;
    }
    


    public CLIENTE(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais){
    	this.ID = id;
    	this.Identificacion = ident;
    	this.Tipo_Cliente = tp;
    	this.Estado = est;
    	this.Fecha_Alta = alta;
    	this.Fecha_Baja = baja;
    	this.Direccion = direc;
    	this.Ciudad = ciudad;
    	this.CodigoPostal = cp;
    	this.Pais = pais;
    }
    
    public CLIENTE(){

    }

    public String getID() {
        return this.ID;
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

    public String getEstado() {
        return this.Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
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

    public String getTipo_Cliente() {
		return Tipo_Cliente;
	}

	public void setTipo_Cliente(String tipo_Cliente) {
		Tipo_Cliente = tipo_Cliente;
	}

	public Date getFecha_Alta() {
		return Fecha_Alta;
	}

	public void setFecha_Alta(Date fecha_Alta) {
		Fecha_Alta = fecha_Alta;
	}

	public Date getFecha_Baja() {
		return Fecha_Baja;
	}

	public void setFecha_Baja(Date fecha_Baja) {
		Fecha_Baja = fecha_Baja;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CLIENTE)) return false;
        CLIENTE cliente = (CLIENTE) o;
        return Objects.equals(getIdentificacion(), cliente.Identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificacion());
    }

	@Override
	public String toString() {
		return "CLIENTE [ID=" + ID + ", Identificacion=" + Identificacion + ", Tipo_Cliente=" + Tipo_Cliente
				+ ", Estado=" + Estado + ", Fecha_Alta=" + Fecha_Alta + ", Fecha_Baja=" + Fecha_Baja + ", Direccion="
				+ Direccion + ", Ciudad=" + Ciudad + ", CodigoPostal=" + CodigoPostal + ", Pais=" + Pais + "]";
	}

}
