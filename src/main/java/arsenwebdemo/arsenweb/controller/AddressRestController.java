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

/**
 * Služi za implementaciju REST API metoda za Address entitete.
 */

@RestController
@RequestMapping("address")
@AllArgsConstructor
public class AddressRestController {

  private AddressService addressService;

  /**
   * Služi za pozivanje HTTP GET metode i vraćanje svih
   * objekata klase Address.
   *
   * @return lista objekata klase Address
   */

  @GetMapping
  public List<Address> getAddresses() {
    return addressService.findAll();
  }

  /**
   * Sprema novi Address entitet.
   *
   * @param address Address objekt koji se sprema
   * @return spremljeni Address objekt
   */

  @PostMapping
  public Address saveNewAddress(@RequestBody Address address) {
    return addressService.save(address);
  }

  /**
   * Ažurira postojeći Address entitet.
   *
   * @param id ID Address entiteta koji se ažurira
   * @param address Address objekt s ažuriranim podacima
   * @return ResponseEntity s ažuriranim Address objektom ili 404 Not Found ako entitet ne postoji
   */

  @PutMapping("/{id}")
  public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
    Optional<Address> addressOptional =
        addressService.updateAddress(address, id);

    return addressOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Briše Address entitet prema ID-u.
   *
   * @param id ID Address entiteta koji se briše
   * @return ResponseEntity sa statusom 204 No Content ako je uspješno obrisan ili 404 Not Found ako entitet ne postoji
   */

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

  /**
   * Pronalaženje Address entiteta prema ID-u.
   *
   * @param id ID Address entiteta koji se traži
   * @return ResponseEntity s pronađenim Address objektom ili 404 Not Found ako entitet ne postoji
   */

  @GetMapping("/{id}")
  public ResponseEntity<Address> findAddressById(@PathVariable Integer id) {
    Optional<Address> addressOptional = addressService.findById(id);
    return addressOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
