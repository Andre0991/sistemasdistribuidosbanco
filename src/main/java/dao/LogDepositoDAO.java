package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.LogDeposito;

import factory.Singleton;

public class LogDepositoDAO {
	
	private List<LogDeposito> historicoDepositos;

	public List<LogDeposito> findAll() {
		return historicoDepositos;
	}

	public List<LogDeposito> findByCliente(final long id) {
		ClienteDAO clienteDAO = Singleton.INSTANCE.getClienteDAO();
		Cliente cliente = clienteDAO.findById(id);
		if (cliente == null) {
			return null;
		}
		String numeracaoContaCorrente = cliente.getContaCorrente().getNumeracao();
		List<LogDeposito> depositosContaCorrentesFromCliente = new ArrayList<LogDeposito>();
		for (LogDeposito logDeposito : historicoDepositos) {
			if (logDeposito.getNumeroConta().equals(numeracaoContaCorrente)) {
				depositosContaCorrentesFromCliente.add(logDeposito);
			}
		}
		return depositosContaCorrentesFromCliente;
	}
	
	public void add(final LogDeposito logDeposito) {
		this.historicoDepositos.add(logDeposito);
	}

	public LogDepositoDAO() {
		super();
		this.historicoDepositos = new ArrayList<LogDeposito>();
	}

}
