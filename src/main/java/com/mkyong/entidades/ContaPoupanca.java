package com.mkyong.entidades;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContaPoupanca {

	private Cliente cliente;

	private double saldo;
	
	private String numeracao;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumeracao() {
		return numeracao;
	}

	public void setNumeracao(String numeracao) {
		this.numeracao = numeracao;
	}
	

}
