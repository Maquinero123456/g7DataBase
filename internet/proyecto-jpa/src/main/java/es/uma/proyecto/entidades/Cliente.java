package es.uma.proyecto.entidades;

import java.util.Objects;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="CLIENTE")
public class Cliente {
    
	@JsonbTransient
    @Id @GeneratedValue
    private long id;
	@JsonbTransient
    @Column(unique=true, nullable=false)
    private String identificacion;
    @Column(nullable=false)
    private String tipoCliente;
    @Column(nullable=false)
    private String estado;
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(nullable=false)
    private String pais;
    @Column(nullable=false)
    private String ciudad;
    @Column(nullable=false)
    private String codigoPostal;
    @Column(nullable=false)
    private String direccion;

    @JsonbTransient
    @OneToMany(mappedBy = "cliente")
    private List<CuentaFintech> cuentas;
    
    @JsonbTransient
    @OneToOne
    @JoinColumn(unique = true)
    private Usuario usuario;

    public Cliente(String ident, String tp, String est, Date alta, String direc, String ciudad, String cp, String pais){
    	this.identificacion = ident;
    	this.tipoCliente = tp;
    	this.estado = est;
    	this.fechaAlta = alta;
    	this.direccion = direc;
    	this.ciudad = ciudad;
    	this.codigoPostal = cp;
    	this.pais = pais;
    }
    


    public Cliente(String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais){
    	this.identificacion = ident;
    	this.tipoCliente = tp;
    	this.estado = est;
    	this.fechaAlta = alta;
    	this.fechaBaja = baja;
    	this.direccion = direc;
    	this.ciudad = ciudad;
    	this.codigoPostal = cp;
    	this.pais = pais;
    }


    public List<CuentaFintech> getCuentas() {
        return this.cuentas;
    }

    public void setCuentas(List<CuentaFintech> cuentas) {
        this.cuentas = cuentas;
    }


    public Cliente(){

    }

    public long getID() {
        return this.id;
    }

    public String getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(String ident) {
        this.identificacion = ident;
    }
    
    public String getTipoCliente() {
        return this.tipoCliente;
    }

    public void setCliente(String cliente) {
        this.tipoCliente = cliente;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String dir) {
        this.direccion = dir;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(String cp) {
        this.codigoPostal = cp;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

	public void setTipoCliente(String tCliente) {
		tipoCliente = tCliente;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fAlta) {
		fechaAlta = fAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fBaja) {
		fechaBaja = fBaja;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getIdentificacion(), cliente.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificacion());
    }

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", identificacion=" + identificacion + ", tipoCliente=" + tipoCliente
				+ ", estado=" + estado + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", direccion="
				+ direccion + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + ", pais=" + pais + "]";
	}

}
