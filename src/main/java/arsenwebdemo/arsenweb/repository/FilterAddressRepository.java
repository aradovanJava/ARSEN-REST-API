package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.model.Address;
import java.util.List;
import java.util.Optional;

public interface FilterAddressRepository extends AddressRepository {
  List<Address> filterByCity(String city);
  List<Address> filterByStreet(String street);
  Optional<Address> findByHighestHouseNumber();
}
