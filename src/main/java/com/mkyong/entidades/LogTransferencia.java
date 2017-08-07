package com.mkyong.entidades;

import java.util.Date;

import common.StatusTransacao;

public class LogTransferencia {
	
	private String contaOrigem;
	private String contaDestino;
	private Double valor;
	
	private Date horario;
	private StatusTransacao status;
	private String msgExcecao;
	
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getContaOrigem() {
		return contaOrigem;
	}
	
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	
	public String getContaDestino() {
		return contaDestino;
	}
	
	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
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
	
	
	
	
		
	
	
	}


