package rest;

import javax.annotation.Generated;

public class ProductsJson {

    private SearchParametersProducts searchParameters;

    public SearchParametersProducts getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParametersProducts searchParameters) {
        this.searchParameters = searchParameters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ProductsJson.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("searchParameters");
        sb.append('=');
        sb.append(((this.searchParameters == null)?"<null>":this.searchParameters));
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
        result = ((result* 31)+((this.searchParameters == null)? 0 :this.searchParameters.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductsJson) == false) {
            return false;
        }
        ProductsJson rhs = ((ProductsJson) other);
        return ((this.searchParameters == rhs.searchParameters)||((this.searchParameters!= null)&&this.searchParameters.equals(rhs.searchParameters)));
    }

}
