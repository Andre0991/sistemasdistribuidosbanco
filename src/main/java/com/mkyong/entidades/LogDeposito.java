package com.mkyong.entidades;

import java.util.Date;

import common.StatusDeposito;

public class LogDeposito {
	
	private Double valor;
	
	private String numeroConta;
	
	private Date horario;
	
	private StatusDeposito status;
	
	private String msgExcecao;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public StatusDeposito getStatus() {
		return status;
	}

	public void setStatus(StatusDeposito status) {
		this.status = status;
	}

	public String getMsgExcecao() {
		return msgExcecao;
	}

	public void setMsgExcecao(String msgExcecao) {
		this.msgExcecao = msgExcecao;
	}

}
