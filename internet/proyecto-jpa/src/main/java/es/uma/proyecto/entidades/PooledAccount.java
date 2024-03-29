package es.uma.proyecto.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="POOLEDACCOUNT")
public class PooledAccount extends CuentaFintech {

	@OneToMany(mappedBy = "pooledAccount")
    private Set<DepositadaEn> depositadaEn = new HashSet<DepositadaEn>();

    public PooledAccount(String iban, String swift, Boolean est, Date alta, Date baja, String clasic){
    	super(iban, swift, est, alta, baja, clasic);
    }

	public String getIban(){
		return super.getIBAN();
	}
	public String getSwift(){
		return super.getSWIFT();
	}

	public Boolean getEstado(){
		return super.getEstado();
	}

	public Date getFechaApertura(){
		return super.getFechaApertura();
	}

	public Date getFechaCierre(){
		return super.getFechaCierre();
	}

	public String getClasficicacion(){
		return super.getClasificacion();
	}

    public PooledAccount() {
        super();
    }


	@Override
	public String toString() {
		return "PooledAccount [getFecha_Apertura()=" + getFechaApertura() + ", getEstado()=" + getEstado()
				+ ", getFecha_Cierre()=" + getFechaCierre() + ", getClasificacion()=" + getClasificacion()
				+ ", getCliente()=" + getCliente() + ", toString()=" + super.toString() + ", getIBAN()=" + getIBAN()
				+ ", getSWIFT()=" + getSWIFT() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + "]";
	}

	public Set<DepositadaEn> getDepositadaEn() {
		return depositadaEn;
	}
}