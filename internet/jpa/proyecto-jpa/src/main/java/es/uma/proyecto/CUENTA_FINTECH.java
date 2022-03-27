package es.uma.proyecto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("CUENTA_FINTECH")
public class CUENTA_FINTECH extends CUENTA {
    @Column(nullable = false)
    private Boolean Estado = false;
    @Column(nullable = false)
    private Date Fecha_Apertura;
    private Date Fecha_Cierre;
    private String Clasificacion;

    public Date getFecha_Apertura() {
        return Fecha_Apertura;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

    public void setFecha_Apertura(Date fecha_Apertura) {
        Fecha_Apertura = fecha_Apertura;
    }

    public Date getFecha_Cierre() {
        return Fecha_Cierre;
    }

    public void setFecha_Cierre(Date fecha_Cierre) {
        Fecha_Cierre = fecha_Cierre;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        Clasificacion = clasificacion;
    }



}