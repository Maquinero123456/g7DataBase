package es.uma.proyecto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EMPRESA")
public class EMPRESA extends CLIENTE {
	
	@Column(nullable = false)
    private String Razon_Social;

	public EMPRESA(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad,
				String cp, String pais, String razon_Social) {
			super(id, ident, tp, est, alta, baja, direc, ciudad, cp, pais);
			Razon_Social = razon_Social;
		}
	
    public void setRazon_Social(String razon_Social) {
        Razon_Social = razon_Social;
    }

    public String getRazon_Social() {
        return Razon_Social;
    }

	@Override
	public String toString() {
		return "EMPRESA [Razon_Social=" + Razon_Social + "]";
	}


}