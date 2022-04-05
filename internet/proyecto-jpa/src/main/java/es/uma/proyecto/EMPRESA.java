package es.uma.proyecto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EMPRESA")
public class EMPRESA extends CLIENTE {
    @Column(nullable = false)
    private String Razon_Social;

    public void setRazon_Social(String razon_Social) {
        Razon_Social = razon_Social;
    }

    public String getRazon_Social() {
        return Razon_Social;
    }


}