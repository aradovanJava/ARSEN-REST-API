package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.model.Address;
import java.util.List;
import java.util.Optional;

public interface AddressRepository {
  List<Address> findAll();
  Address save(Address address);
  Optional<Address> findByHighestHouseNumber();
}
