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
public class Persona_Autorizada {
    
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
    private Boolean Estado;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Inicio;
    @Temporal(TemporalType.DATE)
    private Date FechaFin;

    public Persona_Autorizada(){

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

    public Boolean isEstado() {
        return this.Estado;
    }

    public Boolean getEstado() {
        return this.Estado;
    }

    public void setEstado(Boolean Estado) {
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
        if (!(o instanceof Persona_Autorizada)) {
            return false;
        }
        Persona_Autorizada persona_Autorizada = (Persona_Autorizada) o;
        return Objects.equals(ID, persona_Autorizada.ID) && Objects.equals(Identificacion, persona_Autorizada.Identificacion) && Objects.equals(Nombre, persona_Autorizada.Nombre) && Objects.equals(Apellidos, persona_Autorizada.Apellidos) && Objects.equals(Direccion, persona_Autorizada.Direccion) && Objects.equals(Fecha_Nacimiento, persona_Autorizada.Fecha_Nacimiento) && Objects.equals(Estado, persona_Autorizada.Estado) && Objects.equals(Fecha_Inicio, persona_Autorizada.Fecha_Inicio) && Objects.equals(FechaFin, persona_Autorizada.FechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Identificacion, Nombre, Apellidos, Direccion, Fecha_Nacimiento, Estado, Fecha_Inicio, FechaFin);
    }

}
