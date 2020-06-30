package springboot.remedios.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rh implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String cargo;
	
	private String tipocontracao;
	
	
	private String beneficios;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getTipocontracao() {
		return tipocontracao;
	}


	public void setTipocontracao(String tipocontracao) {
		this.tipocontracao = tipocontracao;
	}


	public String getBeneficios() {
		return beneficios;
	}


	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}
	
	
	
	
	
}
