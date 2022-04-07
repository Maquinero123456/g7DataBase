package es.uma.proyecto;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("POOLED_ACCOUNT")
public class POOLED_ACCOUNT extends CUENTA_FINTECH {

    public POOLED_ACCOUNT(String iban, String swift, Boolean est, Date alta, Date baja, String clasic){
    	super(iban, swift, est, alta, baja, clasic);
    }


    public POOLED_ACCOUNT() {
        super();
    }


	@Override
	public String toString() {
		return "POOLED_ACCOUNT [getFecha_Apertura()=" + getFecha_Apertura() + ", getEstado()=" + getEstado()
				+ ", getFecha_Cierre()=" + getFecha_Cierre() + ", getClasificacion()=" + getClasificacion()
				+ ", getCliente()=" + getCliente() + ", toString()=" + super.toString() + ", getIBAN()=" + getIBAN()
				+ ", getSWIFT()=" + getSWIFT() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + "]";
	}

    
	
}