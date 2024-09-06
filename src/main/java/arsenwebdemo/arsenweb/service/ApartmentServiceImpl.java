package arsenwebdemo.arsenweb.service;

import arsenwebdemo.arsenweb.model.Apartment;
import arsenwebdemo.arsenweb.repository.SpringDataApartmentRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

  private SpringDataApartmentRepository apartmentRepository;

  @Override
  public List<Apartment> findAll() {
    return apartmentRepository.findAll();
  }
}
