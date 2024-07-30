package arsenwebdemo.arsenweb.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Owner extends Person {

    private Set<RealEstate> realEstates;

    public Owner(Integer id, String oib, String firstName, String lastName,
                 LocalDate dateOfBirth,
                 Set<RealEstate> realEstateList)
    {
        super(id, oib, firstName, lastName, dateOfBirth);
        this.realEstates = realEstateList;
    }

    public Set<RealEstate> getRealEstates() {
        return realEstates;
    }

    public void setRealEstateList(Set<RealEstate> realEstates) {
        this.realEstates = realEstates;
    }
}
