
package rest;

import java.util.Objects;

import javax.annotation.Generated;

public class ClientsJson {

    private SearchParametersClients searchParameters;

    public SearchParametersClients getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParametersClients searchParameters) {
        this.searchParameters = searchParameters;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ClientsJson)) {
            return false;
        }
        ClientsJson example = (ClientsJson) o;
        return Objects.equals(searchParameters, example.searchParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(searchParameters);
    }

    @Override
    public String toString() {
        return "{" +
            " searchParameters='" + getSearchParameters() + "'" +
            "}";
    }
    
}
