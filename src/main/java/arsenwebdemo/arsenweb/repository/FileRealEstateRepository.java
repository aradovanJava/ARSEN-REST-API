package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.exception.FileException;
import arsenwebdemo.arsenweb.model.Address;
import arsenwebdemo.arsenweb.model.FileRealEstateTypeConfiguration;
import arsenwebdemo.arsenweb.model.Owner;
import arsenwebdemo.arsenweb.model.RealEstate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class FileRealEstateRepository implements RealEstateRepository {

  @Value("${file.name.realEstates}")
  private String filePath;

  @Autowired
  private AddressRepository addressRepository;

  @Override
  public List<RealEstate> findAll() {

    final List<RealEstate> realEstates = new ArrayList<>();

    try {
      List<String> realEstatesFileLines = Files.readAllLines(Path.of(filePath));

      /*
      if(ownersFileLines.size() % NUMBER_OF_LINES != 0) {
        throw new FileException("The number of lines is not a multiple of " + NUMBER_OF_LINES);
      }

       */

      int i = 0;

      while(true) {

        String realEstateTypeLine =  realEstatesFileLines.get(i++);

        if("END".equals(realEstateTypeLine)) {
          break;
        }

        FileRealEstateTypeConfiguration realEstateType =
            FileRealEstateTypeConfiguration.valueOf(realEstateTypeLine);

        int numberOfLines = realEstateType.getNumberOfLines();

        RealEstate realEstate = null;

        switch(realEstateType) {
          case HOUSE -> {
            Integer id = Integer.parseInt(realEstatesFileLines.get(i++));
            Address address = addressRepository.findById(
                Integer.parseInt(realEstatesFileLines.get(i++))).get();
            //realEstate = new House(id, address);
          }
          case APARTMENT -> {

          }
          default -> throw new FileException("Invalid RealEstateType: " + realEstateType);
        }

        realEstates.add(realEstate);
      }
    } catch (IOException e) {
      String message = "There was a IO problem reading the file '" + filePath + "'";
      log.error(message, e);
      throw new FileException(message, e);
    }

    return realEstates;
  }

  @Override
  public Optional<RealEstate> findTheMostExpensiveRealEstate() {
    return Optional.empty();
  }
}
