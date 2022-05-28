
package rest;

import javax.annotation.Generated;

public class SearchParametersProducts {

    private String status;
    private String productNumber;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SearchParametersProducts.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("productNumber");
        sb.append('=');
        sb.append(((this.productNumber == null)?"<null>":this.productNumber));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        result = ((result* 31)+((this.productNumber == null)? 0 :this.productNumber.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SearchParametersProducts) == false) {
            return false;
        }
        SearchParametersProducts rhs = ((SearchParametersProducts) other);
        return (((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status)))&&((this.productNumber == rhs.productNumber)||((this.productNumber!= null)&&this.productNumber.equals(rhs.productNumber))));
    }

}
