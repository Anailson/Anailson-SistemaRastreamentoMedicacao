package springboot.remedios.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springboot.remedios.model.Rh;

@Repository
@Transactional
public interface RhRepository extends CrudRepository<Rh, Long> {

}
