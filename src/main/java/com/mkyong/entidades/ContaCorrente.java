package com.mkyong.entidades;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonBackReference;

@XmlRootElement
public class ContaCorrente {

	private Cliente cliente;
	private double saldo;
	private String numeracao;
	private List<LogTransacao> historicoTransacoes;

	public List<LogTransacao> getHistoricoTransacoes() {
		return historicoTransacoes;
	}

	public void setHistoricoTransacoes(List<LogTransacao> historicoTransacoes) {
		this.historicoTransacoes = historicoTransacoes;
	}

	@JsonBackReference
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
	
	public boolean temSaldoParaTransferencia(Double valorTransferencia) {
		return this.saldo >= valorTransferencia;
	}
	
	public void addTransacao(LogTransacao logTransacao) {
		this.historicoTransacoes.add(logTransacao);
	}

}
