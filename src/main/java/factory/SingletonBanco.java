package factory;

import java.util.ArrayList;
import java.util.List;
import com.mkyong.entidades.Banco;
import dao.BancoDAO;

public enum SingletonBanco {
	INSTANCE;

	private BancoDAO bancoDAO;

	private SingletonBanco() {

		List<Banco> bancoMock = new ArrayList<Banco>();
		
		// banco1
		Banco banco1 = new Banco();
		banco1.setNome("Banco1");
		banco1.setId(1);
		banco1.setEndereco("localhost:test");
		
		
		// banco2
		Banco banco2 = new Banco();
		banco2.setNome("Banco2");
		banco2.setId(2);
		banco2.setEndereco("localhost:test2");
		

		// bancoDAO
		bancoMock.add(banco1);
		bancoMock.add(banco2);
		this.bancoDAO = new BancoDAO(bancoMock);
		

	}


	public BancoDAO getBancoDAO() {
		return this.bancoDAO;
	}
	

}
