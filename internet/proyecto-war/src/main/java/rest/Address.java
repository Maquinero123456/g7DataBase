package rest;

public class Address {

	private String streetNumber;
	private String postalCode;
	private String city;
	private String country;
	
	public String getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
			sb.append(Address.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
			sb.append("streetNumber");
			sb.append('=');
			sb.append(this.streetNumber);
			sb.append(',');
			sb.append("postalCode");
			sb.append('=');
			sb.append(this.postalCode);
			sb.append(',');
			sb.append("city");
			sb.append('=');
			sb.append(this.city);
			sb.append(',');
			sb.append("country");
			sb.append('=');
			sb.append((this.country));
			sb.append(',');
			if (sb.charAt((sb.length()- 1)) == ',') {
				sb.setCharAt((sb.length()- 1), ']');
			} else {
				sb.append(']');
			}
		return sb.toString();
	}
	
}


