package springboot.remedios.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;

@SuppressWarnings("deprecation")
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private String fornecedor; // EX:MEDCOMERCE ou OU DROGASIL

	private String tipo; // NORMAL OU CONTROLADO

	private String data;

	private String qtd; // QUANTIDADE DE PRODUTO
	
	@OneToMany(mappedBy = "produto") //MAPEANDO COM O OBJETO CRIADO NA CLASSE LOTE
	private List<Lote> lote; //CADA PRODUTO TEM UMA LISTA DE LOTE
	

	public List<Lote> getLote() {
		return lote;
	}

	public void setLote(List<Lote> lote) {
		this.lote = lote;
	}

	public Long getId() {
		return id;
	}

	public String getQtd() {
		return qtd;
	}

	public void setQtd(String qtd) {
		this.qtd = qtd;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
