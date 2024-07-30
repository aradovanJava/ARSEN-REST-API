package arsenwebdemo.arsenweb.repository;

import arsenwebdemo.arsenweb.model.Owner;
import java.util.List;

public interface OwnerRepository {
  List<Owner> findAll();
}
