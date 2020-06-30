package springboot.remedios.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springboot.remedios.model.ClienteFornecedor;
import springboot.remedios.model.Pessoa;
import springboot.remedios.repository.ClienteFornecedorRepository;

@Controller
public class ClienteFornecedorController {
	
 @Autowired	
  private ClienteFornecedorRepository clienteFornecedorRepository;	
	
  @RequestMapping(method = RequestMethod.GET, value = "/clientefornecedor")
	public ModelAndView inicio() {
	  
	  /*PASSANDO OBJETO VAZIO*/
	  ModelAndView modelAndView = new ModelAndView("cadastro/clientefornecedor");
	  modelAndView.addObject("clienteobj", new ClienteFornecedor());
	  Iterable<ClienteFornecedor> clientefornecedorIt = clienteFornecedorRepository.findAll();
	  modelAndView.addObject("clientes", clientefornecedorIt);
	  
	return modelAndView;  
		
		
		
		
	}
	
  /*--------SALVAR----------------------------*/
  @RequestMapping(method =  RequestMethod.POST, value = "**/salvarclientefornecedor") //** NÂO EM CONSIDERAÇÃO O QUE VEM ANTES DA URL E ASSI PEGANDO SOMENTE salvarclientefornecedor
    public ModelAndView salvar(ClienteFornecedor clientefornecedor) {
	  
	    clienteFornecedorRepository.save(clientefornecedor);
	    
	    /*SALVA E CARREGA OS DADOS NA TELA*/
	    ModelAndView andView = new ModelAndView("cadastro/clientefornecedor");
		Iterable<ClienteFornecedor> clientefornecedorIt = clienteFornecedorRepository.findAll();//CAREGA TUDO CADASTRADO NA TABELA
		andView.addObject("clientes", clientefornecedorIt);
		andView.addObject("clienteobj", new ClienteFornecedor());  //CRIANDO SEMPRE UM NOVO OBJETO APOS NOVA AÇÃO
		 
		return andView;
	
	    	
    	
    }
  
  /*LISTA DE CLIENTES E FORNECEDORES*/
  @RequestMapping(method = RequestMethod.GET, value = "/listaclientes")
  public ModelAndView cliente() {
	  ModelAndView andView = new ModelAndView("cadastro/clientefornecedor");
	  Iterable<ClienteFornecedor> clientefornecedorIt = clienteFornecedorRepository.findAll();//CAREGA TUDO CADASTRADO NA TABELA
	  andView.addObject("clientes", clientefornecedorIt);
	  andView.addObject("clienteobj", new ClienteFornecedor()); /*SEMPRE PASSA UM NOVO OBJETO*/
	  return andView;
	  
  }
  
  /*-----------------------EDITAR------------------------------*/
  @GetMapping("/editarcliente/{idcliente}")
  public ModelAndView editar(@PathVariable("idcliente") Long idcliente) {
	     
	  Optional<ClienteFornecedor> clienteFornecedor = clienteFornecedorRepository.findById(idcliente);
	  
	  ModelAndView modelAndView = new ModelAndView("cadastro/clientefornecedor");
	  modelAndView.addObject("clienteobj", clienteFornecedor.get());
	  
	  return modelAndView;
	  
		 
	  
	     
	  }
  
  
  /*-----------------------EXCLUIR------------------------------*/
  @GetMapping("/removercliente/{idcliente}")
  public ModelAndView excluir(@PathVariable("idcliente") Long idcliente) {
	     
	  clienteFornecedorRepository.deleteById(idcliente);
	  
	  ModelAndView modelAndView = new ModelAndView("cadastro/clientefornecedor");
	  modelAndView.addObject("clientes", clienteFornecedorRepository.findAll()); //clientes ->retorna da tabela do html tr
	  modelAndView.addObject("clienteobj", new ClienteFornecedor());//RETORNANDO OBJETO VAZIO AO EXCLUIR - clienteobj ->vem do form
	  
	  return modelAndView;
 
	 	     
	  }
  
  /*-------------------------------CONSULTA----------------------------------------*/
 	@PostMapping("**/pesquisacliente")
	public ModelAndView pesquisar(@RequestParam("nomepesquisatipo")String nomepesquisatipo) {
		ModelAndView modelAndView = new ModelAndView("cadastro/clientefornecedor");
		modelAndView.addObject("clientes", clienteFornecedorRepository.findClienteFornecedorByName(nomepesquisatipo));
		modelAndView.addObject("clienteobj", new ClienteFornecedor());
		
		return modelAndView;
  

 	}

}
