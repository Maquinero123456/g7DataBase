package es.uma.proyecto.entidades;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="INDIVIDUAL")
public class Individual extends Cliente{
    //Atributos
	@JsonbProperty("firstName")
    @Column(nullable = false)
    private String nombre;
	@JsonbProperty("lastName")
    @Column(nullable = false)
    private String apellidos;
	@JsonbProperty(value="dateOfBirth", nillable=true)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public Individual(String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais, String nom, String ape, Date nac) {
    	super(ident, tp, est, alta, baja, direc, ciudad, cp, pais);
    	this.nombre = nom;
    	this.apellidos = ape;
    	this.fechaNacimiento = nac;
    }
    
    public Individual(String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais, String nombre, String apellido) {
    	super(ident, tp, est, alta, baja, direc, ciudad, cp, pais);
        this.nombre = nombre;
        this.apellidos = apellido;
    }
    
    public Individual(){
        super();
    }

    //Setters y Getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

	@Override
	public String toString() {
		return super.toString()+ " Individual [nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Individual other = (Individual) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		
		return true;
	}

}