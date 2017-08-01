package dao;

import java.util.List;

import com.mkyong.entidades.LogConsultaBancoCentral;

public class LogConsultaBancoCentralDAO {

	private List<LogConsultaBancoCentral> historicoConsultas;

	public List<LogConsultaBancoCentral> findAll() {
		return historicoConsultas;
	}
	
	public void add(LogConsultaBancoCentral log) {
		this.historicoConsultas.add(log);
	}

	public LogConsultaBancoCentralDAO(List<LogConsultaBancoCentral> historicoConsultas) {
		super();
		this.historicoConsultas = historicoConsultas;
	}

}
