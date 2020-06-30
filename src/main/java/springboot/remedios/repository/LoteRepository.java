package springboot.remedios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springboot.remedios.model.Lote;

@Repository
@Transactional
public interface LoteRepository extends CrudRepository<Lote, Long>{

	
	@Query("select t from Lote t where t.produto.id = ?1")
    public List<Lote> getLote(Long produtoid);  //LOTE ESTA ASSOCIADO A CADA PESSOA  - CONSULTAAPENAS O LOTE DO PRODUTO
	
}
