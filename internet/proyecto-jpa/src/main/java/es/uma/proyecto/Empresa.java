package es.uma.proyecto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Empresa")
public class Empresa extends Cliente {


	@Column(nullable = false)
    private String razonSocial;

	@OneToMany(mappedBy = "empresa")
    private List<Autorizacion> autorizacion;

	public Empresa(String id, String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad,
				String cp, String pais, String razonSocial) {
			super(id, ident, tp, est, alta, baja, direc, ciudad, cp, pais);
			this.razonSocial = razonSocial;
		}
	
	public Empresa(){
		super();
	}
	
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

	@Override
	public String toString() {
		return "Empresa [razonSocial=" + razonSocial + "]";
	}


}