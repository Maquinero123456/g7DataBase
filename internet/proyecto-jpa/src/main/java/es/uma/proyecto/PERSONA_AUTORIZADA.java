package es.uma.proyecto;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PERSONA_AUTORIZADA {
    
    @Id @GeneratedValue
    private String ID;
    @Column(unique=true, nullable=false)
    private String Identificacion;
    @Column(nullable=false)
    private String Nombre;
    @Column(nullable=false)
    private String Apellidos;
    @Column(nullable=false)
    private String Direccion;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Nacimiento;
    private String Estado;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Inicio;
    @Temporal(TemporalType.DATE)
    private Date FechaFin;

    public PERSONA_AUTORIZADA(String id, String ident, String nom, String ape, String dir, Date nac, String est, Date alta, Date baja){
    	this.ID = id;
    	this.Identificacion = ident;
    	this.Nombre = nom;
    	this.Apellidos = ape;
    	this.Direccion = dir;
    	this.Fecha_Nacimiento = nac;
    	this.Estado = est;
    	this.Fecha_Inicio = alta;
    	this.FechaFin = baja;
    }
    
    public PERSONA_AUTORIZADA(){

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

    public void setIdentificacion(String Identificacion) {
        this.Identificacion = Identificacion;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return this.Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDireccion() {
        return this.Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public Date getFecha_Nacimiento() {
        return this.Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }


    public String getEstado() {
        return this.Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Date getFecha_Inicio() {
        return this.Fecha_Inicio;
    }

    public void setFecha_Inicio(Date Fecha_Inicio) {
        this.Fecha_Inicio = Fecha_Inicio;
    }

    public Date getFechaFin() {
        return this.FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PERSONA_AUTORIZADA)) {
            return false;
        }
        PERSONA_AUTORIZADA pA = (PERSONA_AUTORIZADA) o;
        return Identificacion.equalsIgnoreCase(pA.Identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Identificacion);
    }

	@Override
	public String toString() {
		return "PERSONA_AUTORIZADA [ID=" + ID + ", Identificacion=" + Identificacion + ", Nombre=" + Nombre
				+ ", Apellidos=" + Apellidos + ", Direccion=" + Direccion + ", Fecha_Nacimiento=" + Fecha_Nacimiento
				+ ", Estado=" + Estado + ", Fecha_Inicio=" + Fecha_Inicio + ", FechaFin=" + FechaFin + "]";
	}

}
