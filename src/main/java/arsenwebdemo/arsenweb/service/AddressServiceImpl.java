package arsenwebdemo.arsenweb.service;

import arsenwebdemo.arsenweb.model.Address;
import arsenwebdemo.arsenweb.repository.AddressRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

  private AddressRepository addressRepository;

  @Override
  public List<Address> findAll() {
    return addressRepository.findAll();
  }

  @Override
  public Address save(Address address) {
    return addressRepository.save(address);
  }
}
