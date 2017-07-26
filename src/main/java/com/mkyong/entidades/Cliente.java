package com.mkyong.entidades;

public class Cliente {
	
	private String nome;
	
	private String sobrenome;
	
	private Long id;
	
	private Double saldoContaCorrente;

	private Double saldoContaPoupanca;

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

	public Double getSaldoContaCorrente() {
		return saldoContaCorrente;
	}

	public void setSaldoContaCorrente(Double saldoContaCorrente) {
		this.saldoContaCorrente = saldoContaCorrente;
	}

	public Double getSaldoContaPoupanca() {
		return saldoContaPoupanca;
	}

	public void setSaldoContaPoupanca(Double saldoContaPoupanca) {
		this.saldoContaPoupanca = saldoContaPoupanca;
	}

	public Cliente(String nome, String sobrenome, Long id, Double saldoContaCorrente, Double saldoContaPoupanca) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.id = id;
		this.saldoContaCorrente = saldoContaCorrente;
		this.saldoContaPoupanca = saldoContaPoupanca;
	}

	public Cliente() {
	}

}
