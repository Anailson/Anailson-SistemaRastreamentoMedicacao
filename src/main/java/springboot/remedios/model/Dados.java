package springboot.remedios.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Dados {    /*DADOS DOS COLABOREAS*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String numero; //NUMERO;
	
	private String fone; //FIXO OU CELULAR; -  CARREGAR NO COMBO
	
	private String idade;
	
	private String escolaridade;
	
	private String estadocivil;
	
	private String salario;
	
	private String sexopessoa;
	
	@SuppressWarnings("deprecation")
	@ManyToOne           //MUITOS PARA 1  - N P/ 1
	@ForeignKey(name="pessoa_id")  // CRIANDO UMA CHAVE ESTRAGEIRA
	private Pessoa pessoa;  //CHAMANDO A CLASSE PESSOA E CRIANDO UM OBJETO PARA ELA

	
	/*USANDO O SETs E GETs PARA TODOS OS ATRIBUTOS*/
	
	@DateTimeFormat(pattern = "yyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	
	public Long getId() {
		return id;
	}

	

	public String getSexopessoa() {
		return sexopessoa;
	}



	public void setSexopessoa(String sexopessoa) {
		this.sexopessoa = sexopessoa;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}





	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	
	public String getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
	
	
	

}
