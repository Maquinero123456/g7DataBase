package es.uma.proyecto;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("INDIVIDUAL")
public class INDIVIDUAL extends CLIENTE{
    //Atributos
    @Column(nullable = false)
    private String Nombre;
    @Column(nullable = false)
    private String Apellidos;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Nacimiento;

    public INDIVIDUAL(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais, String nom, String ape, Date nac) {
    	super(id, ident, tp, est, alta, baja, direc, ciudad, cp, pais);
    	this.Nombre = nom;
    	this.Apellidos = ape;
    	this.Fecha_Nacimiento = nac;
    }
    
    public INDIVIDUAL(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad, String cp, String pais) {
    	super(id, ident, tp, est, alta, baja, direc, ciudad, cp, pais);
    }
    
    //Setters y Getters
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public Date getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date fecha_Nacimiento) {
        Fecha_Nacimiento = fecha_Nacimiento;
    }

	@Override
	public String toString() {
		return "INDIVIDUAL [Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", Fecha_Nacimiento=" + Fecha_Nacimiento
				+ "]";
	}

}