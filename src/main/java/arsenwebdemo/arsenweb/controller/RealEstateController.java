package arsenwebdemo.arsenweb.controller;

import arsenwebdemo.arsenweb.model.Apartment;
import arsenwebdemo.arsenweb.model.RealEstate;
import arsenwebdemo.arsenweb.service.RealEstateService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("real-estates")
@AllArgsConstructor
public class RealEstateController {

  private RealEstateService realEstateService;

  @GetMapping
  public List<RealEstate> getRealEstates() {
    return realEstateService.findAll();
  }

}
