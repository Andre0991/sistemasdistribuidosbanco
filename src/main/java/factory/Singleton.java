package factory;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.ContaCorrente;
import com.mkyong.entidades.ContaPoupanca;
import com.mkyong.entidades.LogTransacao;

import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import dao.LogConsultaSaldoDAO;
import dao.LogDepositoDAO;
import dao.LogTedDAO;

public enum Singleton {
	INSTANCE;

	private ClienteDAO clienteDAO;
	private ContaCorrenteDAO contaCorrenteDAO;
	private LogDepositoDAO logDepositoDAO;
	private LogTedDAO logTedDAO;
	private LogConsultaSaldoDAO logConsultaSaldoDAO;


	private Singleton() {

		List<Cliente> clientesMock = new ArrayList<Cliente>();
		// client 1
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Andre");
		cliente1.setSobrenome("PT");
		cliente1.setId(1L);
		ContaCorrente contaCorrente1 = new ContaCorrente();
		contaCorrente1.setCliente(cliente1);
		contaCorrente1.setNumeracao("AAAAA");
		contaCorrente1.setSaldo(10D);
		List<LogTransacao> transacoesCC1 = new ArrayList<LogTransacao>();
		contaCorrente1.setHistoricoTransacoes(transacoesCC1);
		cliente1.setContaCorrente(contaCorrente1);
		ContaPoupanca contaPoupanca1 = new ContaPoupanca();
		contaPoupanca1.setCliente(cliente1);
		contaPoupanca1.setNumeracao("AAAAA");
		contaPoupanca1.setSaldo(10D);
		cliente1.setContaPoupanca(contaPoupanca1);
		
		// client 2
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Iasmin");
		cliente2.setSobrenome("P");
		cliente2.setId(2L);
		ContaCorrente contaCorrente2 = new ContaCorrente();
		contaCorrente2.setCliente(cliente2);
		contaCorrente2.setNumeracao("BBBBB");
		contaCorrente2.setSaldo(20D);
		List<LogTransacao> transacoesCC2 = new ArrayList<LogTransacao>();
		contaCorrente2.setHistoricoTransacoes(transacoesCC2);
		cliente2.setContaCorrente(contaCorrente2);
		ContaPoupanca contaPoupanca2 = new ContaPoupanca();
		contaPoupanca2.setCliente(cliente2);
		contaPoupanca2.setNumeracao("BBBBB");
		contaPoupanca2.setSaldo(20D);
		cliente2.setContaPoupanca(contaPoupanca2);

		// client 3
		Cliente cliente3 = new Cliente();
		cliente3.setNome("Leonardo");
		cliente3.setSobrenome("F");
		cliente3.setId(3L);
		ContaCorrente contaCorrente3 = new ContaCorrente();
		contaCorrente3.setCliente(cliente3);
		contaCorrente3.setNumeracao("CCCCC");
		contaCorrente3.setSaldo(30D);
		List<LogTransacao> transacoesCC3 = new ArrayList<LogTransacao>();
		contaCorrente3.setHistoricoTransacoes(transacoesCC3);
		cliente3.setContaCorrente(contaCorrente3);
		ContaPoupanca contaPoupanca3 = new ContaPoupanca();
		contaPoupanca3.setCliente(cliente3);
		contaPoupanca3.setNumeracao("CCCCC");
		contaPoupanca3.setSaldo(30D);
		cliente3.setContaPoupanca(contaPoupanca3);

		// clienteDAO
		clientesMock.add(cliente1);
		clientesMock.add(cliente2);
		clientesMock.add(cliente3);
		this.clienteDAO = new ClienteDAO(clientesMock);
		
		// contaCorrenteDAO
		List<ContaCorrente> contasCorrentes = new ArrayList<ContaCorrente>(); 
		contasCorrentes.add(contaCorrente1);
		contasCorrentes.add(contaCorrente2);
		contasCorrentes.add(contaCorrente3);
		this.contaCorrenteDAO = new ContaCorrenteDAO(contasCorrentes);
		
		// logDepositoDAO
		this.logDepositoDAO = new LogDepositoDAO();
		this.logTedDAO = new LogTedDAO();
		this.logConsultaSaldoDAO = new LogConsultaSaldoDAO();

	}
	
	public LogDepositoDAO getLogDepositoDAO() {
		return logDepositoDAO;
	}

	public ClienteDAO getClienteDAO() {
		return this.clienteDAO;
	}
	
	public ContaCorrenteDAO getContaCorrenteDAO() {
		return this.contaCorrenteDAO;
	}

	public LogTedDAO getLogTedDAO() {
		return logTedDAO;
	}

	public LogConsultaSaldoDAO getLogConsultaSaldoDAO() {
		return logConsultaSaldoDAO;
	}

}
