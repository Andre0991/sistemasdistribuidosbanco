package com.mkyong.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogConsultaBancoCentral {
	
	private int id;
	private Date horario;
	private int status;
	private String msgExcecao;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsgExcecao() {
		return msgExcecao;
	}
	public void setMsgExcecao(String msgExcecao) {
		this.msgExcecao = msgExcecao;
	}

}
