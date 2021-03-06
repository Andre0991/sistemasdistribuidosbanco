package com.mkyong.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import common.StatusTransacao;

@XmlRootElement
public class LogTed extends LogTransacao {

	private int idBancoDestino;
	private double valor;
	private String contaOrigem;
	private String contaDestino;

	private Date horario;
	private StatusTransacao status;
	private String msgErro;
	private String tipo = "ted";

	public int getIdBancoDestino() {
		return idBancoDestino;
	}
	public void setIdBancoDestino(int idBancoDestino) {
		this.idBancoDestino = idBancoDestino;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getNumeroContaOrigem() {
		return contaOrigem;
	}
	public void setNumeroContaOrigem(String numeroContaOrigem) {
		this.contaOrigem = numeroContaOrigem;
	}
	public String getNumeroContaDestino() {
		return contaDestino;
	}
	public void setNumeroContaDestino(String numeroContaDestino) {
		this.contaDestino = numeroContaDestino;
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
		return msgErro;
	}
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMsgErro() {
		return msgErro;
	}

}
