package arsenwebdemo.arsenweb.controller;

import arsenwebdemo.arsenweb.model.Address;
import arsenwebdemo.arsenweb.service.AddressService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("address")
@AllArgsConstructor
public class AddressRestController {

  private AddressService addressService;

  @GetMapping
  public List<Address> getAddresses() {
    return addressService.findAll();
  }

  @PostMapping
  public Address saveNewAddress(@RequestBody Address address) {
    return addressService.save(address);
  }

}
