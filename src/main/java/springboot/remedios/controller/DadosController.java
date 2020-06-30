package springboot.remedios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import springboot.remedios.repository.DadosRepository;


@Controller
public class DadosController {    /*RESPONSAVEEL POR DADOS FUNCIONARIOS*/

	@Autowired
	private DadosRepository dadosRepository;
	

	
}
