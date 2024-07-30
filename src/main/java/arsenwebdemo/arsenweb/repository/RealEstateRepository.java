package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.model.RealEstate;
import java.util.List;
import java.util.Optional;

public interface RealEstateRepository {
  List<RealEstate> findAll();
  Optional<RealEstate> findTheMostExpensiveRealEstate();
}
