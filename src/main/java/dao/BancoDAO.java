package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Banco;

public class BancoDAO {
	
	private List<Banco> bancosMock;
	
	public BancoDAO(List<Banco> bancosMock) {
		super();
		this.bancosMock = bancosMock;
	}

	public Banco findById(int id) {
		Banco bancoRetorno = null;
		for (final Banco banco : this.bancosMock) {
			if (banco.getId() == id) {
				bancoRetorno = banco;
			}
		}
		return bancoRetorno;
	}

}