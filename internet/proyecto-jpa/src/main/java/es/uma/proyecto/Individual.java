package es.uma.proyecto;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("Individual")
public class Individual extends Cliente{
    //Atributos
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public Individual(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais, String nom, String ape, Date nac) {
    	super(id, ident, tp, est, alta, baja, direc, ciudad, cp, pais);
    	this.nombre = nom;
    	this.apellidos = ape;
    	this.fechaNacimiento = nac;
    }
    
    public Individual(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais, String nombre, String apellido) {
    	super(id, ident, tp, est, alta, baja, direc, ciudad, cp, pais);
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
		return "Individual [nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento
				+ "]";
	}

}