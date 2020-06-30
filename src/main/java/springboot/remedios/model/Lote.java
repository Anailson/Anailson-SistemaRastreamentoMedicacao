package springboot.remedios.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Formula;

@Entity
public class Lote implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String numero;  //NUMERO DO LOTE
	
	private String datalote;
	
	private String responsavel;  //farmaceutico
	
	private String crfresponsavel;  //Conselho Federal de Farmácia
	
	
	/*IDENTIFICANDO A CLASSE NA TABELA PAI(PRODUTO)  1 PRA N*/

	
	
	@org.hibernate.annotations.ForeignKey(name = "produto_id")
	@ManyToOne  //MUITOS PRA 1 
	private Produto produto;


	public Long getId() {
		return id;
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


	public String getDatalote() {
		return datalote;
	}


	public void setDatalote(String datalote) {
		this.datalote = datalote;
	}


	public String getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}


	public String getCrfresponsavel() {
		return crfresponsavel;
	}


	public void setCrfresponsavel(String crfresponsavel) {
		this.crfresponsavel = crfresponsavel;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
	
	
	
	

	
	

}
