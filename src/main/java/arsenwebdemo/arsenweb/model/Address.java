package arsenwebdemo.arsenweb.model;

import arsenwebdemo.arsenweb.enumeration.City;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@jakarta.persistence.Entity
public class Address extends Entity {

    private String street;

    @Enumerated(EnumType.STRING)
    private City city;
    private Integer houseNumber;
    private String houseNumberAddOn;

    public Address(Integer id, String street, City city, Integer houseNumber) {
        super(id);
        this.houseNumber = houseNumber;
        this.city = city;
        this.street = street;
    }

    public Address(String street, City city, Integer houseNumber) {
        this.houseNumber = houseNumber;
        this.city = city;
        this.street = street;
    }
}
