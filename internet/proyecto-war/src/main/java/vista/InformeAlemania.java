package vista;

import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.commons.csv.CSVPrinter;

import es.uma.proyecto.GestionInformes;

@Named(value = "IFA")
@RequestScoped
public class InformeAlemania {

	@EJB
	private GestionInformes informes;
	
    private CSVPrinter sfile;
    
    private CSVPrinter mfile;

    public InformeAlemania() {
    	// INSTANCIA
    }

	public CSVPrinter getSfile() {
		return sfile;
	}

	public void setSfile() throws IOException {
		this.sfile = informes.informeSemanalAlemania();
	}

	public CSVPrinter getMfile() {
		return mfile;
	}

	public void setMfile() throws IOException {
		this.mfile = informes.informeMensualAlemania();
	}

}