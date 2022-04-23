package es.uma.proyecto;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("PooledAccount")
public class PooledAccount extends CuentaFintech {

	@OneToMany(mappedBy = "pooledAccount")
    private List<DepositadaEn> depositadaEn;

    public PooledAccount(String iban, String swift, Boolean est, Date alta, Date baja, String clasic){
    	super(iban, swift, est, alta, baja, clasic);
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

    
	
}