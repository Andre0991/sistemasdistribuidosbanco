package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.LogSaque;
import com.mkyong.entidades.LogTed;

import factory.Singleton;

public class LogTedDAO {

	private List<LogTed> historicoTeds;

	public List<LogTed> findAll() {
		return historicoTeds;
	}
	
	
	/*procura os TEDs onde o id participa, usando como parametro o numeroDaConta, sem usar o id do banco, 
	poderiamos estabelecer que não existem numeros de contas iguais mesmo em bancos diferentes*/
	
	public List<LogTed> findByCliente(final long id) {
		ClienteDAO clienteDAO = Singleton.INSTANCE.getClienteDAO();
		Cliente cliente = clienteDAO.findById(id);
		if (cliente == null) {
			return null;
		}
		String numeracaoContaCorrente = cliente.getContaCorrente().getNumeracao();
		List<LogTed> tedsContaCorrentesFromCliente = new ArrayList<LogTed>();
		for (LogTed logTeds: historicoTeds) {
			//sender
			if (logTeds.getNumeroContaOrigem().equals(numeracaoContaCorrente)) {
				tedsContaCorrentesFromCliente.add(logTeds);
			}
			//receiver
			else if(logTeds.getNumeroContaDestino().equals(numeracaoContaCorrente)) {
				tedsContaCorrentesFromCliente.add(logTeds);
			}
		}
		return tedsContaCorrentesFromCliente;
	}
	
	public void add(final LogTed logTed) {
		this.historicoTeds.add(logTed);
	}

	public LogTedDAO() {
		super();
		this.historicoTeds = new ArrayList<LogTed>();
	}

}
