package arsenwebdemo.arsenweb.controller;

import arsenwebdemo.arsenweb.model.Apartment;
import arsenwebdemo.arsenweb.service.ApartmentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apartment")
@AllArgsConstructor
public class ApartmentRestController {

  private ApartmentService apartmentService;

  @GetMapping
  public List<Apartment> getApartments() {
    return apartmentService.findAll();
  }

}
