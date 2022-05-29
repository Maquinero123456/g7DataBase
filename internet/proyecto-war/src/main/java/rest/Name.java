package rest;

import java.util.HashMap;
import java.util.Map;
public class Name {
	
	private String firstName;
	private String lastName;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public String getFirstName() {
	return firstName;
	}
	
	public void setFirstName(String firstName) {
	this.firstName = firstName;
	}
	
	public String getLastName() {
	return lastName;
	}
	
	public void setLastName(String lastName) {
	this.lastName = lastName;
	}
	
	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}
	
	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
	
	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder();
		sb.append(Name.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
		sb.append("firstName");
		sb.append('=');
		sb.append((this.firstName));
		sb.append(',');
		sb.append("lastName");
		sb.append('=');
		sb.append((this.lastName));
		sb.append(',');
		if (sb.charAt((sb.length()- 1)) == ',') {
			sb.setCharAt((sb.length()- 1), ']');
		} else {
			sb.append(']');
		}
	return sb.toString();
	}

}