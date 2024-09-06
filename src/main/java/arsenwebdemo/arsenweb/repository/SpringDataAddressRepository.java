package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.model.Address;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAddressRepository extends JpaRepository<Address, Integer> {

}
