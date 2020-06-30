package springboot.remedios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springboot.remedios.model.Dados;

@Repository
@Transactional
public interface DadosRepository extends CrudRepository<Dados, Long> {
	
	 
	/*METODO PARA CARREGA QD É FEITA ASSOCIOAÇÃO DE PAI/FILHO*/
	@Query("select d from Dados  d where d.pessoa.id = ?1")
	public List<Dados> getDados(Long pessoaid);

}
