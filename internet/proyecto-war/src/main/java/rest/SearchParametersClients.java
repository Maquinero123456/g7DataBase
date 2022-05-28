
package rest;

import java.util.Objects;

import javax.annotation.Generated;

public class SearchParametersClients {

    private NameClients name;
    private String startPeriod;
    private String endPeriod;

    public NameClients getName() {
        return name;
    }

    public void setName(NameClients name) {
        this.name = name;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SearchParametersClients)) {
            return false;
        }
        SearchParametersClients searchParameters = (SearchParametersClients) o;
        return Objects.equals(name, searchParameters.name) && Objects.equals(startPeriod, searchParameters.startPeriod) && Objects.equals(endPeriod, searchParameters.endPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startPeriod, endPeriod);
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", startPeriod='" + getStartPeriod() + "'" +
            ", endPeriod='" + getEndPeriod() + "'" +
            "}";
    }

}
