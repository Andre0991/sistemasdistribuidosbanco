package factory;

import java.util.ArrayList;
import java.util.List;
import com.mkyong.entidades.Banco;
import com.mkyong.entidades.LogConsultaBancoCentral;

import dao.BancoDAO;
import dao.LogConsultaBancoCentralDAO;

public enum SingletonBanco {
	INSTANCE;

	private BancoDAO bancoDAO;
	private LogConsultaBancoCentralDAO logConsultaBancoCentralDAO;

	public LogConsultaBancoCentralDAO getLogConsultaBancoCentralDAO() {
		return logConsultaBancoCentralDAO;
	}


	private SingletonBanco() {

		List<Banco> bancoMock = new ArrayList<Banco>();
		
		// banco1
		Banco banco1 = new Banco();
		banco1.setNome("Banco1");
		banco1.setId(1);
		banco1.setEndereco("http://localhost:8080/RESTfulExample/rest/");
		
		
		// banco2
		Banco banco2 = new Banco();
		banco2.setNome("Banco2");
		banco2.setId(2);
		banco2.setEndereco("http://localhost:9080/RESTfulExample/rest/");
		

		// bancoDAO
		bancoMock.add(banco1);
		bancoMock.add(banco2);
		this.bancoDAO = new BancoDAO(bancoMock);
		
		// LogConsultaBancoCentralDAO
		List<LogConsultaBancoCentral> historicoConsultas = new ArrayList<LogConsultaBancoCentral>();
		this.logConsultaBancoCentralDAO = new LogConsultaBancoCentralDAO(historicoConsultas);

	}


	public BancoDAO getBancoDAO() {
		return this.bancoDAO;
	}
	

}
