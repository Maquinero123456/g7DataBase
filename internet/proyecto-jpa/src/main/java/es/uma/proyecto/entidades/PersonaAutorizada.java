package es.uma.proyecto.entidades;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PERSONAAUTORIZADA")
public class PersonaAutorizada {
    
	@JsonbTransient
    @Id @GeneratedValue
    private Long id;
	@JsonbTransient
    @Column(unique=true, nullable=false)
    private String identificacion;
	@JsonbProperty("firstName")
    @Column(nullable=false)
    private String nombre;
	@JsonbProperty("lastName")
    @Column(nullable=false)
    private String apellidos;
	@JsonbProperty("streetNumber")
    @Column(nullable=false)
    private String direccion;
	@JsonbProperty(value="dateOfBirth", nillable=true)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @JsonbProperty("status")
    private String estado;
    @JsonbTransient
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @JsonbTransient
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @OneToMany(mappedBy = "persona")
    private List<Autorizacion> autorizacion;
    
    @OneToOne
    @JoinColumn(unique = true)
    private Usuario usuario;

    public PersonaAutorizada( String ident, String nom, String ape, String dir, Date nac, String est, Date alta, Date baja){
    	this.identificacion = ident;
    	this.nombre = nom;
    	this.apellidos = ape;
    	this.direccion = dir;
    	this.fechaNacimiento = nac;
    	this.estado = est;
    	this.fechaInicio = alta;
    	this.fechaFin = baja;
    }
    
    public PersonaAutorizada(){

    }

    public Long getID() {
        return this.id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<Autorizacion> getAutorizacion() {
        return this.autorizacion;
    }

    public void setAutorizacion(List<Autorizacion> autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_Nacimiento() {
        return this.fechaNacimiento;
    }

    public void setFecha_Nacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_Inicio() {
        return this.fechaInicio;
    }

    public void setFecha_Inicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonaAutorizada)) {
            return false;
        }
        PersonaAutorizada pA = (PersonaAutorizada) o;
        return identificacion.equalsIgnoreCase(pA.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificacion);
    }

	@Override
	public String toString() {
		return "PersonaAutorizada [id=" + id + ", identificacion=" + identificacion + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", direccion=" + direccion + ", fechaNacimiento=" + fechaNacimiento
				+ ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}

}
