package arsenwebdemo.arsenweb.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
public class Apartment extends RealEstate {

    private Integer floorNumber;

    public Apartment(Integer id, Address address, BigDecimal area,
                     BigDecimal price, Integer numberOfRooms,
                     Integer numberOfBalconies, Integer floorNumber,
                     List<Owner> ownerList)
    {
        super(id, address, area, price, numberOfRooms, numberOfBalconies, ownerList);
        this.floorNumber = floorNumber;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }
}
