package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.enumeration.City;
import arsenwebdemo.arsenweb.model.Address;
import arsenwebdemo.arsenweb.model.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class MockAddressRepository implements FilterAddressRepository {

  private static final List<Address> addressList = new ArrayList<>();

  static {
    Address firstHouseAddress =
        new Address(1,
            "Paljetkova",
            City.ZAGREB,
            18);

    Address secondHouseAddress =
        new Address(2,
            "Slavonska avenija",
            City.ZAGREB,
            102);

    Address firstAparmentAddress =
        new Address(3,
            "Dubrava",
                  City.ZAGREB,
            5);

    Address secondAparmentAddress =
        new Address(4,
            "Tratinska",
            City.ZAGREB,
            50);

    addressList.add(firstHouseAddress);
    addressList.add(secondHouseAddress);
    addressList.add(firstAparmentAddress);
    addressList.add(secondAparmentAddress);
  }

  MockAddressRepository() {}

  @Override
  public List<Address> findAll() {
    return addressList;
  }

  @Override
  public List<Address> filterByCity(String city) {
    return addressList.stream()
        .filter(a -> a.getCity().name().contains(city.toUpperCase()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Address> filterByStreet(String street) {
    return addressList.stream()
        .filter(a -> a.getStreet().toUpperCase().contains(street.toUpperCase()))
        .collect(Collectors.toList());
  }

  @Override
  public Address save(Address address) {
    Integer nextId = addressList.stream()
        .map(Entity::getId)
        .reduce(0, Integer::max);

    address.setId(nextId + 1);
    addressList.add(address);
    return address;
  }

  @Override
  public Optional<Address> findByHighestHouseNumber() {
    return addressList.stream()
        .max((a1, a2) -> a1.getHouseNumber().compareTo(a2.getHouseNumber()));
  }
}
