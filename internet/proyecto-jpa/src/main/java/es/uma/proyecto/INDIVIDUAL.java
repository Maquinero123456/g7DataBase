package es.uma.proyecto;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("INDIVIDUAL")
public class INDIVIDUAL extends CLIENTE{
    //Atributos
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public INDIVIDUAL(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais, String nom, String ape, Date nac) {
    	super(id, ident, tp, est, alta, baja, direc, ciudad, cp, pais);
    	this.nombre = nom;
    	this.apellidos = ape;
    	this.fechaNacimiento = nac;
    }
    
    public INDIVIDUAL(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais, String nombre, String apellido) {
    	super(id, ident, tp, est, alta, baja, direc, ciudad, cp, pais);
        this.nombre = nombre;
        this.apellidos = apellido;
    }
    
    public INDIVIDUAL(){
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
		return "INDIVIDUAL [nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento
				+ "]";
	}

}