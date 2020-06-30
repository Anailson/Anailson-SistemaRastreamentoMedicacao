package springboot.remedios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springboot.remedios.model.Produto;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	@Query("select p from Produto p where p.fornecedor like  %?1%")
	List<Produto> findProdutoByFornecedor(String fornecedor);

}
