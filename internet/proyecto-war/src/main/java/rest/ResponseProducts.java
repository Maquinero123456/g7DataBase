package rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value="products")
@RequestScoped
public class ResponseProducts {

	private Individuo individual;
	
	public Individuo getIndividual() {
		return individual;
	}
	
	public void setIndividual(Individuo individual) {
		this.individual = individual;
	}

	
	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
		sb.append(ResponseProducts.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
		sb.append("individual");
		sb.append('=');
		sb.append((this.individual));
		sb.append(',');
		if (sb.charAt((sb.length()- 1)) == ',') {
		sb.setCharAt((sb.length()- 1), ']');
		} else {
		sb.append(']');
		}
		return sb.toString();
	}

}