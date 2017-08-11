package com.mkyong.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mkyong.entidades.LogDeposito;
import com.mkyong.entidades.LogSaque;
import com.mkyong.entidades.LogTed;
import com.mkyong.entidades.LogTransferencia;

import dao.LogDepositoDAO;
import dao.LogSaqueDAO;
import dao.LogTedDAO;
import dao.LogTransferenciaDAO;
import factory.Singleton;

@Path("/log")
public class LogService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/depositos")
	public List<LogDeposito> listAllLogDeposito() {
		LogDepositoDAO logDepositoDAO = Singleton.INSTANCE.getLogDepositoDAO();
		List<LogDeposito> listalogDeposito = logDepositoDAO.findAll();
		return listalogDeposito;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/saques")
	public List<LogSaque> listAllLogSaque() {
		LogSaqueDAO logSaqueDAO = Singleton.INSTANCE.getLogSaqueDAO();
		List<LogSaque> listalogSaque = logSaqueDAO.findAll();
		return listalogSaque;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/teds")
	public List<LogTed> listAllLogTed() {
		LogTedDAO logTedDAO = Singleton.INSTANCE.getLogTedDAO();
		List<LogTed> listalogTed = logTedDAO.findAll();
		return listalogTed;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/transferencias")
	public List<LogTransferencia> listAllLogTransferencia() {
		LogTransferenciaDAO logTransferenciaDAO = Singleton.INSTANCE.getLogTransferenciaDAO();
		List<LogTransferencia> listalogTransferencia = logTransferenciaDAO.findAll();
		return listalogTransferencia;
	}
}
