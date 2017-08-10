package com.mkyong.entidades;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonManagedReference;

@XmlRootElement
public class Cliente {
	
	private String nome;
	
	private String sobrenome;
	
	private Long id;
	
    @JsonManagedReference
	private ContaCorrente contaCorrente;

    @JsonManagedReference
	private ContaPoupanca contaPoupanca;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public ContaPoupanca getContaPoupanca() {
		return contaPoupanca;
	}

	public void setContaPoupanca(ContaPoupanca contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}
	
	


}
