package springboot.remedios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springboot.remedios.repository.RhRepository;

@Controller
public class RhController {
	
	@Autowired
	private RhRepository rhRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/rh")
	public String inicio() {
		
		return "cadastro/rh";
		
	}

}
