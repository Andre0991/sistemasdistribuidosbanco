package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.LogConsultaSaldo;

public class LogConsultaSaldoDAO {
	
	private List<LogConsultaSaldo> historicoConsultaSaldos;

	public List<LogConsultaSaldo> findAll() {
		return historicoConsultaSaldos;
	}
	
	public void add(final LogConsultaSaldo logConsultaSaldo) {
		this.historicoConsultaSaldos.add(logConsultaSaldo);
	}

	public LogConsultaSaldoDAO() {
		super();
		this.historicoConsultaSaldos = new ArrayList<LogConsultaSaldo>();
	}

}
