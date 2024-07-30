package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.enumeration.City;
import arsenwebdemo.arsenweb.exception.FileException;
import arsenwebdemo.arsenweb.model.Address;
import arsenwebdemo.arsenweb.model.Entity;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Primary
public class FileAddressRepository implements FilterAddressRepository {

  @Value("${file.name.addresses}")
  private String filePath;

  private static final Integer NUMBER_OF_LINES = 5;

  @Override
  public List<Address> findAll() throws FileException {

    final List<Address> addresses = new ArrayList<>();


    try {
      List<String> addressFileLines = Files.readAllLines(Path.of(filePath));

      if(addressFileLines.size() % NUMBER_OF_LINES != 0) {
        throw new FileException("The number of lines is not a multiple of " + NUMBER_OF_LINES);
      }

      for(int i = 0; i < addressFileLines.size()/NUMBER_OF_LINES; i += 1) {
        Integer id = Integer.parseInt(addressFileLines.get(i * NUMBER_OF_LINES));
        String street = addressFileLines.get((i * NUMBER_OF_LINES) + 1);
        City city = City.valueOf(addressFileLines.get((i * NUMBER_OF_LINES) + 2));
        Integer houseNumber = Integer.parseInt(addressFileLines.get((i * NUMBER_OF_LINES) + 3));
        String houseNumberAddOn = addressFileLines.get((i * NUMBER_OF_LINES) + 4);

        Address newAddress = new Address(id, street, city, houseNumber);

        if(!houseNumberAddOn.isBlank()) {
          newAddress.setHouseNumberAddOn(houseNumberAddOn);
        }

        addresses.add(newAddress);
      }

    } catch (IOException e) {
      String message = "There was a IO problem reading the file '" + filePath + "'";
      log.error(message, e);
      throw new FileException(message, e);
    }

    return addresses;
  }

  @Override
  public List<Address> filterByCity(String city) {
    return findAll().stream()
        .filter(a -> a.getCity().name().contains(city.toUpperCase()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Address> filterByStreet(String street) {
    return findAll().stream()
        .filter(a -> a.getStreet().toUpperCase().contains(street.toUpperCase()))
        .collect(Collectors.toList());
  }

  @Override
  public Address save(Address address) {
    List<Address> addresses = findAll();

    Integer nextId = addresses.stream()
        .map(Entity::getId)
        .reduce(0, Integer::max);

    address.setId(nextId + 1);

    addresses.add(address);

    saveAllAddresses(addresses);

    return address;
  }

  private void saveAllAddresses(List<Address> addresses) {
    try(PrintWriter pw = new PrintWriter(filePath)) {

      for(Address tempAddress : addresses) {
        pw.println(tempAddress.getId());
        pw.println(tempAddress.getStreet());
        pw.println(tempAddress.getCity());
        pw.println(tempAddress.getHouseNumber());
        if(Optional.ofNullable(tempAddress.getHouseNumberAddOn()).isEmpty()) {
          pw.println("");
        }
        else {
          pw.println(tempAddress.getHouseNumberAddOn());
        }
      }
    }
    catch(FileNotFoundException e) {
      String message = "There was a IO problem writing to the file '" + filePath + "'";
      log.error(message, e);
      throw new FileException(message, e);
    }
  }

  @Override
  public Optional<Address> findById(Integer id) {
    return findAll().stream().filter(a -> id.equals(a.getId())).findFirst();
  }

  @Override
  public Optional<Address> updateAddress(Address addressData, Integer id) {

    Optional<Address> addressToUpdate = findById(id);

    if(addressToUpdate.isPresent()) {
      Address address = addressToUpdate.get();
      address.setCity(addressData.getCity());
      address.setHouseNumber(addressData.getHouseNumber());
      address.setStreet(addressData.getStreet());
      address.setHouseNumberAddOn(addressData.getHouseNumberAddOn());

      List<Address> addresses = findAll();

      List<Address> updatedAddresses = new ArrayList<>();

      for(Address tempAddress : addresses) {
        if(!tempAddress.getId().equals(id)) {
          updatedAddresses.add(tempAddress);
        }
        else {
          updatedAddresses.add(address);
        }
      }

      /*
      for(int i = 0; i < addresses.size(); i += 1) {
        if(addresses.get(i).getId().equals(id)) {
          addresses.set(i, address);
        }
      }
       */

      saveAllAddresses(updatedAddresses);
    }



    return addressToUpdate;
  }

  @Override
  public void deleteById(Integer id) {
    List<Address> addresses = findAll();
    if(addresses.removeIf(a -> id.equals(a.getId()))) {
      saveAllAddresses(addresses);
    }
  }

  @Override
  public Optional<Address> findByHighestHouseNumber() {
    return findAll().stream()
        .max((a1, a2) -> a1.getHouseNumber().compareTo(a2.getHouseNumber()));
  }
}
