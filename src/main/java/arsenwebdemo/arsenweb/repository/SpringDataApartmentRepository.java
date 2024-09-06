package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataApartmentRepository extends JpaRepository<Apartment, Integer> {

}
