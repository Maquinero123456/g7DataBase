package es.uma.proyecto.exceptions;

public class PooledAccountException extends Exception{
    
    public PooledAccountException () {
		
	};
	
	public PooledAccountException(String message) {
		super(message);
	}
}