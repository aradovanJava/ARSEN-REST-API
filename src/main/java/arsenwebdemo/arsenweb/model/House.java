package arsenwebdemo.arsenweb.model;

import java.math.BigDecimal;
import java.util.List;

public class House extends RealEstate {

    private BigDecimal yardArea;
    private Integer numberOfFloors;

    public House(Integer id, Address address, BigDecimal area, BigDecimal price,
                 Integer numberOfRooms, Integer numberOfBalconies,
                 BigDecimal yardArea, Integer numberOfFloors,
                 List<Owner> ownerList)
    {
        super(id, address, area, price, numberOfRooms, numberOfBalconies, ownerList);
        this.yardArea = yardArea;
        this.numberOfFloors = numberOfFloors;
        for(Owner owner : ownerList) {
            owner.getRealEstates().add(this);
        }
    }

    public BigDecimal getYardArea() {
        return yardArea;
    }

    public void setYardArea(BigDecimal yardArea) {
        this.yardArea = yardArea;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(Integer numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
}
