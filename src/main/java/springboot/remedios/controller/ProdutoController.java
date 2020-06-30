package springboot.remedios.controller;

import java.util.Optional;

import org.aspectj.weaver.ltw.LTWWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springboot.remedios.model.Lote;
import springboot.remedios.model.Pessoa;
import springboot.remedios.model.Produto;
import springboot.remedios.repository.LoteRepository;
import springboot.remedios.repository.ProdutoRepository;

@Controller
public class ProdutoController {  /*PRODUTO DA EMPRESA*/
	
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private LoteRepository loteRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/produto")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/produto");

		modelAndView.addObject("produtoobj", new Produto()); //PASSANDO UM NOVO OBJETO AO INICIAR A NOVA TELA DE CADASTRO
		
		/*AO ENTRA NA TELA DE INICIO OS DADOS JÃ SÃO CARREGADOS*/
		Iterable<Produto> produtoIt = produtoRepository.findAll();
		modelAndView.addObject("produtos", produtoIt);
		
		return modelAndView; 
		
	
		
	}
	
	/*SALVA*/
	@RequestMapping(method = RequestMethod.POST, value = "/salvaproduto")
	public ModelAndView  salvar(Produto produto) {
		   produtoRepository.save(produto);
		   
		   ModelAndView andView = new ModelAndView("cadastro/produto");
		   Iterable<Produto> produtoIt = produtoRepository.findAll();
		   andView.addObject("produtos", produtoIt);
		   
		   
		   return andView;
	}
	
	/*LISTA DOS DADOS*/

	@RequestMapping(method = RequestMethod.GET, value = "/listaprodutos")
	public ModelAndView produtos () {
		
		ModelAndView andView = new ModelAndView("cadastro/produto");
		Iterable<Produto> produtoIt = produtoRepository.findAll();
		andView.addObject("produtos", produtoIt);
		andView.addObject("produtoobj", new Produto());
		
		return andView;
		

		
		
	}
	
	/*EXCLUIR*/
	@GetMapping("/removerproduto/{idproduto}")
	public ModelAndView excluir(@PathVariable("idproduto") Long idproduto) {
		
		produtoRepository.deleteById(idproduto);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/produto");
		modelAndView.addObject("produto", produtoRepository.findAll());
		modelAndView.addObject("produtoobj", new Produto());
		
		return modelAndView;
		
		
		
	}
	
	/*---------------CONSULTA-------------*/
	
	@PostMapping("**/pesquisaproduto")
	public ModelAndView pesquisa(@RequestParam("fornecedorpesquisa")String fornecedorpesquisa) {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/produto");
		modelAndView.addObject("produtos", produtoRepository.findProdutoByFornecedor(fornecedorpesquisa));
		modelAndView.addObject("produtoobj", new Produto());
		
		return modelAndView;
	}
	
	/*CHAMADA PARA A TELA DE LOTE  SALVADO OS DADOS DA FILHA(LOTE)*/
	
	@PostMapping("**/loteinformacoes/{idproduto}") //É UMA AO REQUESTMAPPIN ESSA ANOTAÇÃO É MAIS NOVA DO SPRING
	public ModelAndView Addloteinfo(Lote lotes, @PathVariable("idproduto") Long idproduto) {//PEGA A VARIAVEL DO HTML CRIADA idpessoa
		
		//Optional<Produto> produto = produtoRepository.findById(produtoid) ;	//BUSCA NO BANCO O IDproduto
		
		Produto produto = produtoRepository.findById(idproduto).get();//CARREGANDO PESSOA
		
		ModelAndView modelAndView = new ModelAndView("cadastro/lote"); 
		
		lotes.setProduto(produto);//GUARDA OS DADOS NA PESSOA(PAI)
		
		loteRepository.save(lotes); //SALVA OS DADOS(FILH0)
		
		modelAndView.addObject("produtoobj", produto);//PEGA OS DADOS DO FORM DA d. E insert na class pessoa(PAI)
		
		modelAndView.addObject("lote", loteRepository.getLote(idproduto));
		/*CHAMANDO DO FORM E DO REPOSTIORY APOS FAZER A QUERY pessoaid É O PAI DOS DADOS getDados(pessoaid)passa a lista de dados so da pessao*/
		
		
		return modelAndView;  //ADICIONANO DOS DADOS NA TELA
		
	}
	
	
	
}
