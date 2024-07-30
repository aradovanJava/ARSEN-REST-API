package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.enumeration.City;
import arsenwebdemo.arsenweb.exception.FileException;
import arsenwebdemo.arsenweb.model.Address;
import arsenwebdemo.arsenweb.model.Owner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class FileOwnerRepository implements OwnerRepository {

  @Value("${file.name.owners}")
  private String filePath;

  private static final Integer NUMBER_OF_LINES = 5;

  @Override
  public List<Owner> findAll() {

    final List<Owner> owners = new ArrayList<Owner>();

    try {
      List<String> ownersFileLines = Files.readAllLines(Path.of(filePath));

      if(ownersFileLines.size() % NUMBER_OF_LINES != 0) {
        throw new FileException("The number of lines is not a multiple of " + NUMBER_OF_LINES);
      }

      for(int i = 0; i < ownersFileLines.size()/NUMBER_OF_LINES; i += 1) {
        Integer id = Integer.parseInt(ownersFileLines.get(i * NUMBER_OF_LINES));
        String oib = ownersFileLines.get((i * NUMBER_OF_LINES) + 1);
        String firstName = ownersFileLines.get((i * NUMBER_OF_LINES) + 2);
        String lastName = ownersFileLines.get((i * NUMBER_OF_LINES) + 3);
        String dateOfBirthString = ownersFileLines.get((i * NUMBER_OF_LINES) + 4);

        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        Owner newOwner = new Owner(id, oib, firstName, lastName, dateOfBirth, new HashSet<>());

        owners.add(newOwner);
      }

    } catch (IOException e) {
      String message = "There was a IO problem reading the file '" + filePath + "'";
      log.error(message, e);
      throw new FileException(message, e);
    }

    return owners;
  }
}
