package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.LogSaque;
import com.mkyong.entidades.LogTransferencia;

import factory.Singleton;


public class LogTransferenciaDAO {

		private List<LogTransferencia> historicoTransferencia;

		public List<LogTransferencia> findAll() {
			return historicoTransferencia;
		}
		
		
		//traz as transferencias que envolvem o id, seja ele sender ou receiver
		public List<LogTransferencia> findByCliente(final long id) {
			ClienteDAO clienteDAO = Singleton.INSTANCE.getClienteDAO();
			Cliente cliente = clienteDAO.findById(id);
			if (cliente == null) {
				return null;
			}
			String numeracaoContaCorrente = cliente.getContaCorrente().getNumeracao();
			List<LogTransferencia> transferenciasContaCorrentesFromCliente = new ArrayList<LogTransferencia>();
			for (LogTransferencia logTransferencias : historicoTransferencia) {
				//se for o receiver 
				if (logTransferencias.getContaDestino().equals(numeracaoContaCorrente)) {
					transferenciasContaCorrentesFromCliente.add(logTransferencias);
				}
				//se for o sender
				else if(logTransferencias.getContaOrigem().equals(numeracaoContaCorrente)) {
					transferenciasContaCorrentesFromCliente.add(logTransferencias);
				}
			}
			return transferenciasContaCorrentesFromCliente;
		}
		
		
		public void add(final LogTransferencia logTransferencia) {
			this.historicoTransferencia.add(logTransferencia);
		}

		public LogTransferenciaDAO() {
			super();
			this.historicoTransferencia = new ArrayList<LogTransferencia>();
		}
	}