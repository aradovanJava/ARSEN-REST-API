package arsenwebdemo.arsenweb.service;

import arsenwebdemo.arsenweb.model.Address;
import java.util.List;
import java.util.Optional;

public interface AddressService {
  List<Address> findAll();
  Address save(Address address);
  Optional<Address> updateAddress(Address address, Integer id);
  void deleteAddress(Integer id);
  Optional<Address> findById(Integer id);
}