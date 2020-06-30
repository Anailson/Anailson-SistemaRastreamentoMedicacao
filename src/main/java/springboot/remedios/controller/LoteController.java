package springboot.remedios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import springboot.remedios.model.Dados;
import springboot.remedios.model.Lote;
import springboot.remedios.model.Pessoa;
import springboot.remedios.model.Produto;
import springboot.remedios.repository.LoteRepository;
import springboot.remedios.repository.ProdutoRepository;

@Controller
public class LoteController  {
	
	@Autowired
	private LoteRepository loteRepository;
	
	
		

		
	
	
	
}

