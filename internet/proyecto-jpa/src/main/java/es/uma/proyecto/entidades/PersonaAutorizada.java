package es.uma.proyecto.entidades;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PersonaAutorizada {
    
    @Id @GeneratedValue
    private Long id;
    @Column(unique=true, nullable=false)
    private String identificacion;
    @Column(nullable=false)
    private String nombre;
    @Column(nullable=false)
    private String apellidos;
    @Column(nullable=false)
    private String direccion;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private String estado;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @OneToMany(mappedBy = "persona")
    private List<Autorizacion> autorizacion;
    
    @OneToOne
    @JoinColumn(unique = true)
    private Usuario usuario;

    public PersonaAutorizada(Long id, String ident, String nom, String ape, String dir, Date nac, String est, Date alta, Date baja){
    	this.id = id;
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
