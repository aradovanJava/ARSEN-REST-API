package arsenwebdemo.arsenweb.service;

import arsenwebdemo.arsenweb.model.RealEstate;
import arsenwebdemo.arsenweb.repository.SpringDataApartmentRepository;
import arsenwebdemo.arsenweb.repository.SpringDataHouseRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {

  private SpringDataApartmentRepository apartmentRepository;
  private SpringDataHouseRepository houseRepository;
  private RealEstateRepository realEstateRepository;

  @Override
  public List<RealEstate> findAll() {
    //realEstates.addAll(apartmentRepository.findAll());
    //realEstates.addAll(houseRepository.findAll());
    return new ArrayList<>(realEstateRepository.findAll());
  }
}
