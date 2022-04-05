package es.uma.proyecto;

import javax.persistence.*;
import java.sql.Date;

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

}