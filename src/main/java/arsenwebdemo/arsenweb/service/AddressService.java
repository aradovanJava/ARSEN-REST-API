package arsenwebdemo.arsenweb.service;

import arsenwebdemo.arsenweb.model.Address;
import java.util.List;

public interface AddressService {
  List<Address> findAll();
  Address save(Address address);
}
