package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Banco;

public class BancoDAO {
	
	private List<Banco> bancosMock;
	
	
	public BancoDAO() {
		super();
		this.bancosMock = new ArrayList<Banco>();
		
		//banco1 
		Banco banco1 = new Banco();
		banco1.setId(1);
		banco1.setNome_banco("banco1");
		
		
		//banco2 
		Banco banco2 = new Banco();
		banco2.setId(2);
		banco2.setNome_banco("banco2");
	
	}
	
	
	public BancoDAO(List<Banco> bancosMock) {
		super();
		this.bancosMock = bancosMock;
	}

	public Banco findById(int id) {
		Banco bancoRetorno = null;
		for (final Banco banco : this.bancosMock) {
			if (banco.getid() == id) {
				bancoRetorno = banco;
			}
		}
		return bancoRetorno;
	}

}