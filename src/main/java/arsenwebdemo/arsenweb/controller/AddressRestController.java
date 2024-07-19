package arsenwebdemo.arsenweb.controller;

import arsenwebdemo.arsenweb.model.Address;
import arsenwebdemo.arsenweb.service.AddressService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PutMapping("/{id}")
  public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
    Optional<Address> addressOptional =
        addressService.updateAddress(address, id);

    return addressOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAddress(@PathVariable Integer id) {
    if(addressService.findById(id).isPresent()) {
      addressService.deleteAddress(id);
      return ResponseEntity.noContent().build();
    }
    else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Address> findAddressById(@PathVariable Integer id) {
    Optional<Address> addressOptional = addressService.findById(id);
    return addressOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
