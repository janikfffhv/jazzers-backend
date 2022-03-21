package at.fhv.jazzers.backend.domain.model.customer;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Address {
    // Properties
    private String streetName;

    private String postalCode;

    private String city;

    private String country;



    // Constructors
    protected Address() {

    }

    public Address(String streetName, String postalCode, String city, String country) {
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }



    // Getters
    public String streetName() {
        return streetName;
    }

    public String postalCode() {
        return postalCode;
    }

    public String city() {
        return city;
    }

    public String country() {
        return country;
    }



    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetName.equals(address.streetName) && postalCode.equals(address.postalCode) && city.equals(address.city) && country.equals(address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, postalCode, city, country);
    }
}
