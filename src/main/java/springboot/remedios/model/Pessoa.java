package springboot.remedios.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;





@Entity
public class Pessoa implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull(message="Nome não pode ser Nulo")
	@NotEmpty(message ="Nome não pode ser vazio")
	private String nome;
	
	@NotNull(message="Sobrenome não pode ser Nulo")
	@NotEmpty(message ="Sobrenome não pode ser vazio")
	private String sobrenome;
	
	private String login;
	
	private String senha;
	
	private String perfil;  /*USUARIO COM PERFIL ADMINISTRADOR OU USUARIO DO SISTEMA OU RH ALTERAR PARA PERFIL*/
	
	private String cpf;
	
	private String rg;
	
	private String ativo; /*IDENTIFICAR QUAIS OS USUÁRIOS ATIVO OU NÃO DO SISTEMA*/
	//Solucao- https://hibernate.atlassian.net/browse/HHH-9940
    @OneToMany(mappedBy = "pessoa", orphanRemoval = false, cascade = CascadeType.ALL) //1 PARA MUITOS     E MAPEANDO O OBJETO PESSOA CRIANDO NA CLASS DADOS                
	private List<Dados> dados;                    /*CRIANDO UM LISTA PRA CHAMAR A CLASS DADOS QUE CONTE A CHAVE PRIMARIA--CascadeType.ALL*/
	
	
	
	public List<Dados> getDados() {
		return dados;
	}

	public void setDados(List<Dados> dados) {
		this.dados = dados;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	
	
	
	

}
