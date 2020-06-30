package springboot.remedios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springboot.remedios.model.ClienteFornecedor;

@Repository
@Transactional
public interface ClienteFornecedorRepository extends CrudRepository<ClienteFornecedor, Long>{
	
/*EDICAÇÃO PRA CHAMAR NO BANCO*/
	//JPQL SELECT DO OBJETO p from ClienteFornecedor
	@Query("select p from ClienteFornecedor p where p.tipo like %?1%")
	List<ClienteFornecedor> findClienteFornecedorByName(String tipo );   /*FAZENDO A BUSCA POR TIPO DE CLIENTE OU FORNECEDOR*/

}
