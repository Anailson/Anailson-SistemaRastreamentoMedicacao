package springboot.remedios.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springboot.remedios.model.Dados;
import springboot.remedios.model.Pessoa;
import springboot.remedios.repository.DadosRepository;
import springboot.remedios.repository.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;  /*CRIAR APOS A CRIAÇÃO DA INTERFACE DO REPOSITORY*/

	@Autowired
	private DadosRepository dadosRepository; /*CHAMANDO O REPOSITORY PRA USA NA DELA DE DADOS*/
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", new Pessoa()); //PASSANDO UM NOVO OBJETO AO INICIAR A NOVA TELA DE CADASTRO
		/*AO ENTRA NA TELA DE INICIO OS DADOS JÃ SÃO CARREGADOS*/
		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
		modelAndView.addObject("pessoas", pessoaIt);
		return modelAndView; 
	}
	
	/*--------------SALVAR E RETORNA PARA A MESMA TELA-------------------------*/
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")//PEGAR OS DADOS DO FORMULARIO
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult bindingResult) {  //@VALID BINDINGRESUL PRA VALIDAR AS MSGS
		
		/*ANTES DE SALVAR É FEITA AS VALIDAÇÕS*/
		if (bindingResult.hasErrors()) {  /*SER TIVER ERRO ENTRA NA CODIÇÃO E VOLTA NA MESMA TELA*/
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
			Iterable<Pessoa> pessoaIt = pessoaRepository.findAll(); //consulta todos
			modelAndView.addObject("pessoas", pessoaIt);
			modelAndView.addObject("pessoaobj", pessoa); //Passando o objeto da view pessoa
			
			List<String> msg = new ArrayList<String>(); //LISTA DE MSG
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());//VEM DAS ANOTAÇÕES DA CLASS PESSOA - @NotNull E OUTRAS
				
			}
			
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		pessoaRepository.save(pessoa);
		
		/*ALTERANDO O METODO PARA SALVAR A LISTA PESSOAS*/
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
	    andView.addObject("pessoas", pessoaIt);
	    andView.addObject("pessoaobj", new Pessoa());
		
		//return "cadastro/cadastropessoa";//SALVA E RETORNA PARA O MESMO CADASTRO
	    return andView; /*RETORNANDO COM OS DADOS PARA TELA*/
	}
	
	/*LISTA DE PESSOAS - LISTA DE DADOS DAS PESSOAS*/
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView pessoas() { /*MODELO DE DADOS QUE MOSTRAR NA TELA*/
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");/*INSTANCIANDO UM OBJETO DA ModelAndViewm*/
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();/*CARREGANDO NO BANCO A LISTA CADASTRADA - USANDO O REPOSITORY QUE É A PARTE DO CRUD*/
	    andView.addObject("pessoas", pessoasIt);/*ADICIONA A LISTA NOS ATRIBUTOS INCLUINDO OS DADOS PARA O OBJETO PESSSOA JOGA A LISTA*/
	    andView.addObject("pessoaobj", new Pessoa());
	    return andView;
		
	}
	
	/*----------------------EDITAR USUARIOS---------------------------*/
	@GetMapping("/editarpessoa/{idpessoa}") //É UMA AO REQUESTMAPPIN ESSA ANOTAÇÃO É MAIS NOVA DO SPRING
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {//PEGA A VARIAVEL DO HTML CRIADA idpessoa
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);//BUSCA NO BANCO O IDPESSOA
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa"); //AO EDITAR RETORNA PARA A TELA DE CADASTRO E CARREGAND O OBJPESSOA PARA EDICAÇÃO
		modelAndView.addObject("pessoaobj", pessoa.get()); //PASSAR O OBJETO PARA A TELA
		
		return modelAndView;  //ADICIONANO DOS DADOS NA TELA
		
		
		
	}
	/*-------------------------------------EXCLUIR remove e carrega todos É IGUAL AO METODO EDITAR-------------------------------------*/
	
  	
	@GetMapping("/removerpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {
		
		/*excluir*/
		pessoaRepository.deleteById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findAll());  //APOS REMOVER CARREGA TODAS AS PESSOAS
		modelAndView.addObject("pessoaobj", new Pessoa());//RETORNANDO OBJETO VAZIO AO EXCLUIR
		return modelAndView;  
	
	}
	
	/*---------------CONSULTA-------------*/
	@PostMapping("**/pesquisapessoa")
	public ModelAndView pesquisa(@RequestParam("nomepesquisa")String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
		modelAndView.addObject("pessoaobj", new Pessoa());
		
		return modelAndView;
		
		
	}
	
	/*NOVO METODO PARA NOVA TELA...DADOSFUNCIONAIS - UTILIZAR COMO BASE O METODO EDITAR*/
	@GetMapping("/dadosfuncionais/{idpessoa}") //É UMA AO REQUESTMAPPIN ESSA ANOTAÇÃO É MAIS NOVA DO SPRING
	public ModelAndView dadosfuncionais(@PathVariable("idpessoa") Long idpessoa) {//PEGA A VARIAVEL DO HTML CRIADA idpessoa
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);//BUSCA NO BANCO O IDPESSOA
		
		ModelAndView modelAndView = new ModelAndView("cadastro/dadosfuncionais"); //AO EDITAR RETORNA PARA A TELA DE CADASTRO E CARREGAND O OBJPESSOA PARA EDICAÇÃO
		modelAndView.addObject("pessoaobj", pessoa.get()); //PASSAR O OBJETO PARA A TELA
		modelAndView.addObject("dados", dadosRepository.getDados(idpessoa));/*CARREGANDO A LISTA DE DADOS CADASTRADA*/
		return modelAndView;  //ADICIONANO DOS DADOS NA TELA
		
		
		
	}
	
	/*NOVO METODO QUE VAI SALVAR OS DADOS DA PAGINA FILHA (DADOS) NO PAI(PESSOAS)*/
	/*PEGA O ID DA CLASSE PAI(PESSOA) E SÃO INJETADOS NA CLASSE FILHA(DADOS) , OU SEJA SELECIONAR O ID DA CLASSE PAI E SALVA NOVOS DADOS (FILHO)*/

	@PostMapping("**/addDadosPessoa/{pessoaid}")  //CHAMADA MAPEAMENTO DA HTML dados funcionais
 	public ModelAndView addDadosPessoa(Dados dados, @PathVariable("pessoaid") Long pessoaid) { //PEGANDO O OBJEJETO
		
		
		Pessoa pessoa = pessoaRepository.findById(pessoaid).get();  //CARREGANDO PESSOA
		
		/*VALIDAÇÃO DO LADO SERVIDOR  -INICIO*/
		if(dados != null && dados.getNumero().isEmpty() || dados.getFone().isEmpty() ) {  //SER UM OU OUTRO FOR VAZIO ENTRA NA VALDAÇÃO
			//SER FOR VAZIO RETORNA PARA MESMA TELA DE CADASTRO
		ModelAndView modelAndView = new ModelAndView("cadastro/dadosfuncionais");  //SENDO NULO RETORNA NA TELA DE CADASTRO
			modelAndView.addObject("pessoaobj", pessoa);
			modelAndView.addObject("dados", dadosRepository.getDados(pessoaid));//CARREGANDO OS DADOS
			
			List<String> msg= new ArrayList<String>();
			
			if(dados.getNumero().isEmpty()) { //Caso o campo esteja vazio

				msg.add("Número deve ser informado");
				}
			
			if(dados.getFone().isEmpty()) {

					msg.add("Tipo deve ser informado. ");
			}
			
			
			modelAndView.addObject("msg", msg);
			
		return modelAndView;
			
				
			 
		}/*FIM*/
		
		ModelAndView modelAndView = new ModelAndView("cadastro/dadosfuncionais"); 
		
		dados.setPessoa(pessoa);//GUARDA OS DADOS NA PESSOA(PAI)
	
		dadosRepository.save(dados); //SALVA OS DADOS(FILH0)
		
		
		//ModelAndView modelAndView = new ModelAndView("cadastro/dadosfuncionais");  //CADASTRANDO RETORNA PARA A MESMA TELA
		modelAndView.addObject("pessoaobj", pessoa);//PEGA OS DADOS DO FORM DA dadosfuncionais. E insert na class pessoa(PAI)
		modelAndView.addObject("dados", dadosRepository.getDados(pessoaid));/*CHAMANDO DO FORM E DO REPOSTIORY APOS FAZER A QUERY pessoaid É O PAI DOS DADOS getDados(pessoaid)passa a lista de dados so da pessao*/
		
		return modelAndView;
		
		
	}
	
	/*REMOVE DADOS  - EXCLUIR*/
	@GetMapping("**/removerdados/{iddados}")
	public ModelAndView removerdados(@PathVariable("iddados") Long iddados) {
		
		Pessoa pessoa = dadosRepository.findById(iddados).get().getPessoa();
		
		dadosRepository.deleteById(iddados);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/dadosfuncionais");
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("dados", dadosRepository.getDados(pessoa.getId()));  //CARREGA OS DADOS APOS A EXCLUSÃO
		return modelAndView;  
	
	}
	
	
	
	
	
}
