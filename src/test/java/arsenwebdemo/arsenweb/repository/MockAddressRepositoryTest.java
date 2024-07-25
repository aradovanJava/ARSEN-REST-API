package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.enumeration.City;
import arsenwebdemo.arsenweb.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class MockAddressRepositoryTest {

  private MockAddressRepository repository;

  @BeforeEach
  void setUp() {
    repository = new MockAddressRepository();
  }

  @Test
  @Order(1)
  void shouldReturnAllAddresses() {
    List<Address> addresses = repository.findAll();
    assertNotNull(addresses);
    assertEquals(4, addresses.size());
  }

  @Test
  @Order(2)
  void shouldFilterByCity() {
    List<Address> addresses = repository.filterByCity("ZAGREB");
    assertNotNull(addresses);
    assertEquals(4, addresses.size());

    addresses = repository.filterByCity("NON_EXISTENT_CITY");
    assertNotNull(addresses);
    assertTrue(addresses.isEmpty());
  }

  @Test
  @Order(3)
  void shouldFilterByStreet() {
    List<Address> addresses = repository.filterByStreet("Paljetkova");
    assertNotNull(addresses);
    assertEquals(1, addresses.size());
    assertEquals("Paljetkova", addresses.get(0).getStreet());

    addresses = repository.filterByStreet("NON_EXISTENT_STREET");
    assertNotNull(addresses);
    assertTrue(addresses.isEmpty());
  }

  @Test
  @Order(4)
  void shouldSaveNewAddress() {
    Address newAddress = new Address();
    newAddress.setCity(City.ZAGREB);
    newAddress.setStreet("New Street");
    newAddress.setHouseNumber(99);

    Address savedAddress = repository.save(newAddress);
    assertNotNull(savedAddress);
    assertEquals(5, savedAddress.getId());
    assertEquals("New Street", savedAddress.getStreet());
    assertEquals(City.ZAGREB, savedAddress.getCity());
    assertEquals(99, savedAddress.getHouseNumber());

    List<Address> addresses = repository.findAll();
    assertEquals(5, addresses.size());
  }

  @Test
  @Order(5)
  void shouldFindByHighestHouseNumber() {
    Optional<Address> addressOptional = repository.findByHighestHouseNumber();
    assertTrue(addressOptional.isPresent());
    assertEquals(102, addressOptional.get().getHouseNumber());
  }

  @Test
  @Order(6)
  void shouldFindById() {
    Optional<Address> addressOptional = repository.findById(1);
    assertTrue(addressOptional.isPresent());
    assertEquals(1, addressOptional.get().getId());

    addressOptional = repository.findById(999);
    assertFalse(addressOptional.isPresent());
  }

  @Test
  @Order(7)
  void shouldUpdateExistingAddress() {
    Address updateData = new Address();
    updateData.setCity(City.ZAGREB);
    updateData.setStreet("Updated Street");
    updateData.setHouseNumber(123);

    Optional<Address> updatedAddress = repository.updateAddress(updateData, 1);
    assertTrue(updatedAddress.isPresent());
    assertEquals("Updated Street", updatedAddress.get().getStreet());
    assertEquals(City.ZAGREB, updatedAddress.get().getCity());
    assertEquals(123, updatedAddress.get().getHouseNumber());

    updatedAddress = repository.updateAddress(updateData, 999);
    assertFalse(updatedAddress.isPresent());
  }

  @Test
  @Order(8)
  void shouldDeleteById() {
    repository.deleteById(1);
    Optional<Address> addressOptional = repository.findById(1);
    assertFalse(addressOptional.isPresent());

    int initialSize = repository.findAll().size();
    repository.deleteById(999); // No exception should be thrown
    int newSize = repository.findAll().size();
    assertEquals(initialSize, newSize); // Size should remain the same
  }
}

