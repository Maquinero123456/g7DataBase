
package rest;

import java.util.Objects;

import javax.annotation.Generated;

public class NameClients {

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NameClients)) {
            return false;
        }
        NameClients name = (NameClients) o;
        return Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lastName);
    }

    @Override
    public String toString() {
        return "{" +
            " lastName='" + getLastName() + "'" +
            "}";
    }


}
