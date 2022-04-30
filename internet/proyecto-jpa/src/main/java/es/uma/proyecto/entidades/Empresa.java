package es.uma.proyecto.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EMPRESA")
public class Empresa extends Cliente {


	@Column(nullable = false)
    private String razonSocial;

	@OneToMany(mappedBy = "empresa")
    private List<Autorizacion> autorizacion;

	public Empresa(String ident, String tp, String est, Date alta, Date baja, String direc, String ciudad,
				String cp, String pais, String razonSocial) {
			super(ident, tp, est, alta, baja, direc, ciudad, cp, pais);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((autorizacion == null) ? 0 : autorizacion.hashCode());
		result = prime * result + ((razonSocial == null) ? 0 : razonSocial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (autorizacion == null) {
			if (other.autorizacion != null)
				return false;
		} else if (!autorizacion.equals(other.autorizacion))
			return false;
		if (razonSocial == null) {
			if (other.razonSocial != null)
				return false;
		} else if (!razonSocial.equals(other.razonSocial))
			return false;
		return true;
	}


}