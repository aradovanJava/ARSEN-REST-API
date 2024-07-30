package arsenwebdemo.arsenweb.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import arsenwebdemo.arsenweb.model.Address;
import arsenwebdemo.arsenweb.model.RealEstate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class FileRealEstateRepositoryTest {

  @Autowired
  private FileRealEstateRepository repository;

  @BeforeEach
  void setUp() {
    //repository = new FileRealEstateRepository();
  }

  @Test
  @Order(1)
  void shouldReturnAllRealEstates() {
    List<RealEstate> realEstates = repository.findAll();
    assertNotNull(realEstates);
    assertEquals(1, realEstates.size());
  }

}
