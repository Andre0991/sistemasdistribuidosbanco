package com.mkyong.rest;

import java.util.List;

import com.mkyong.entidades.LogDeposito;
import com.mkyong.entidades.LogSaque;
import com.mkyong.entidades.LogTed;
import com.mkyong.entidades.LogTransferencia;

import dao.LogDepositoDAO;
import dao.LogSaqueDAO;
import dao.LogTedDAO;
import dao.LogTransferenciaDAO;
import factory.Singleton;

public class LogService {
	public List<LogDeposito> listAllLogDeposito() {
		LogDepositoDAO logDepositoDAO = Singleton.INSTANCE.getLogDepositoDAO();
		List<LogDeposito> listalogDeposito = logDepositoDAO.findAll();
		return listalogDeposito;
	}
	
	public List<LogSaque> listAllLogSaque() {
		LogSaqueDAO logSaqueDAO = Singleton.INSTANCE.getLogSaqueDAO();
		List<LogSaque> listalogSaque = logSaqueDAO.findAll();
		return listalogSaque;
	}
	
	public List<LogTed> listAllLogTed() {
		LogTedDAO logTedDAO = Singleton.INSTANCE.getLogTedDAO();
		List<LogTed> listalogTed = logTedDAO.findAll();
		return listalogTed;
	}
	
	public List<LogTransferencia> listAllLogTransferencia() {
		LogTransferenciaDAO logTransferenciaDAO = Singleton.INSTANCE.getLogTransferenciaDAO();
		List<LogTransferencia> listalogTransferencia = logTransferenciaDAO.findAll();
		return listalogTransferencia;
	}
}
