package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataHouseRepository extends JpaRepository<House, Integer> {

}
