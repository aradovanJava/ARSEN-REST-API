package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.model.Address;
import java.util.List;
import java.util.Optional;

public interface AddressRepository {
  List<Address> findAll();
  Address save(Address address);
  Optional<Address> findById(Integer id);
  Optional<Address> updateAddress(Address addressData, Integer id);
  void deleteById(Integer id);
}
