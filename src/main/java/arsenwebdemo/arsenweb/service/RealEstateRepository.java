package arsenwebdemo.arsenweb.service;

import arsenwebdemo.arsenweb.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {

}
