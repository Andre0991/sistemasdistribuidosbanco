package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.LogDeposito;

public class LogDepositoDAO {
	
	private List<LogDeposito> historicoDepositos;

	public List<LogDeposito> findAll() {
		return historicoDepositos;
	}
	
	public void add(final LogDeposito logDeposito) {
		this.historicoDepositos.add(logDeposito);
	}

	public LogDepositoDAO() {
		super();
		this.historicoDepositos = new ArrayList<LogDeposito>();
	}

}
