package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.LogDeposito;
import com.mkyong.entidades.LogSaque;

import factory.Singleton;

public class LogSaqueDAO {
	private List<LogSaque> historicoSaques;

	public List<LogSaque> findAll() {
		return historicoSaques;
	}
	
	public List<LogSaque> findByCliente(final long id) {
		ClienteDAO clienteDAO = Singleton.INSTANCE.getClienteDAO();
		Cliente cliente = clienteDAO.findById(id);
		if (cliente == null) {
			return null;
		}
		String numeracaoContaCorrente = cliente.getContaCorrente().getNumeracao();
		List<LogSaque> saquesContaCorrentesFromCliente = new ArrayList<LogSaque>();
		for (LogSaque logSaques : historicoSaques) {
			if (logSaques.getNumeroConta().equals(numeracaoContaCorrente)) {
				saquesContaCorrentesFromCliente.add(logSaques);
			}
		}
		return saquesContaCorrentesFromCliente;
	}
	
	public void add(final LogSaque logSaque) {
		this.historicoSaques.add(logSaque);
	}

	public LogSaqueDAO() {
		super();
		this.historicoSaques = new ArrayList<LogSaque>();
	}
}
