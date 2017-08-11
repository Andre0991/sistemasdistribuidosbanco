package com.mkyong.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import common.StatusTransacao;

@XmlRootElement
public class LogSaque extends LogTransacao {
	private Double valor;
	private String numeroConta;

	private Date horario;
	private StatusTransacao status;
	private String msgExcecao;
	private String tipo = "saque";

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

	public StatusTransacao getStatus() {
		return status;
	}

	public void setStatus(StatusTransacao status) {
		this.status = status;
	}

	public String getMsgExcecao() {
		return msgExcecao;
	}

	public void setMsgExcecao(String msgExcecao) {
		this.msgExcecao = msgExcecao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
