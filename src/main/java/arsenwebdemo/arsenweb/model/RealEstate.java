package arsenwebdemo.arsenweb.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
public abstract non-sealed class RealEstate extends Entity implements Transferable {

    @ManyToOne
    private Address address;
    @Transient
    private BigDecimal area;
    @Transient
    private BigDecimal price;
    @Transient
    private Integer numberOfRooms;
    @Transient
    private Integer numberOfBalconies;
    @Transient
    private List<Owner> ownerList;

    protected RealEstate(Integer id, Address address, BigDecimal area, BigDecimal price,
                         Integer numberOfRooms, Integer numberOfBalconies,
                         List<Owner> ownerList)
    {
        super(id);
        this.address = address;
        this.area = area;
        this.price = price;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBalconies = numberOfBalconies;
        this.ownerList = ownerList;
    }



    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getNumberOfBalconies() {
        return numberOfBalconies;
    }

    public void setNumberOfBalconies(Integer numberOfBalconies) {
        this.numberOfBalconies = numberOfBalconies;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public void transferOwnership(List<Owner> newOwnersList) {
        this.ownerList.clear();
        this.ownerList.addAll(newOwnersList);
    }

    @Override
    public String toString() {
        return "RealEstate{" +
                "address=" + address +
                ", area=" + area +
                ", price=" + price +
                ", numberOfRooms=" + numberOfRooms +
                ", numberOfBalconies=" + numberOfBalconies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RealEstate that = (RealEstate) o;
        return Objects.equals(address, that.address) && Objects.equals(area,
            that.area) && Objects.equals(price, that.price) && Objects.equals(
            numberOfRooms, that.numberOfRooms) && Objects.equals(numberOfBalconies,
            that.numberOfBalconies) && Objects.equals(ownerList, that.ownerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, area, price, numberOfRooms, numberOfBalconies, ownerList);
    }
}
