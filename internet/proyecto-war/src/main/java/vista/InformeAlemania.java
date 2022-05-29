package vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import es.uma.proyecto.GestionInformes;

@Named(value = "infal")
@RequestScoped
public class InformeAlemania implements Serializable{

	private static final long serialVersionUID = 1L; 
	
	@EJB
	private GestionInformes informes;

    public InformeAlemania() {
    	// INSTANCIA
    }

	public void descargaMensual() throws IOException, ParseException { 
		informes.informeMensualAlemania();
		File file = new File("informeMensualAlemania.csv"); 
		InputStream fis = new FileInputStream(file); 
		byte[] buf = new byte[1024]; 
		int offset = 0; 
		int numRead = 0; 
		while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) { 
			offset += numRead; 
		} 
		
		fis.close(); 
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance() .getExternalContext().getResponse();
		response.setContentType("application/octet-stream"); 
		response.setHeader("Content-Disposition", "attachment;filename=informeMensualAlemania.csv"); 
		response.getOutputStream().write(buf); 
		response.getOutputStream().flush(); 
		response.getOutputStream().close(); FacesContext.getCurrentInstance().responseComplete(); 
	} 
	
	public void descargaSemanal() throws IOException, ParseException { 
		informes.informeSemanalAlemania();
		File file = new File("informeSemanalAlemania.csv"); 
		InputStream fis = new FileInputStream(file); 
		byte[] buf = new byte[1024]; 
		int offset = 0; 
		int numRead = 0; 
		while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) { 
			offset += numRead; 
		} 
		
		fis.close(); 
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance() .getExternalContext().getResponse();
		response.setContentType("application/octet-stream"); 
		response.setHeader("Content-Disposition", "attachment;filename=informeSemanalAlemania.csv"); 
		response.getOutputStream().write(buf); 
		response.getOutputStream().flush(); 
		response.getOutputStream().close(); FacesContext.getCurrentInstance().responseComplete(); 
	} 
}
