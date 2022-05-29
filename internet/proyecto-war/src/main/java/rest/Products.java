package rest;


public class Products {

	private String productNumber;
	private String status;
	private String relationship;

	public String getProductNumber() {
	return productNumber;
	}

	public void setProductNumber(String productNumber) {
	this.productNumber = productNumber;
	}

	public String getStatus() {
	return status;
	}

	public void setStatus(String status) {
	this.status = status;
	}

	public String getRelationship() {
	return relationship;
	}

	public void setRelationship(String relationship) {
	this.relationship = relationship;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Products.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
		sb.append("productNumber");
		sb.append('=');
		sb.append(this.productNumber);
		sb.append(',');
		sb.append("status");
		sb.append('=');
		sb.append(this.status);
		sb.append(',');
		sb.append("relationship");
		sb.append('=');
		sb.append(this.relationship);
		sb.append(',');
		if (sb.charAt((sb.length()- 1)) == ',') {
			sb.setCharAt((sb.length()- 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}