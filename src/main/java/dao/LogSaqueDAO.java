package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.LogSaque;

public class LogSaqueDAO {
	private List<LogSaque> historicoSaques;

	public List<LogSaque> findAll() {
		return historicoSaques;
	}
	
	public void add(final LogSaque logSaque) {
		this.historicoSaques.add(logSaque);
	}

	public LogSaqueDAO() {
		super();
		this.historicoSaques = new ArrayList<LogSaque>();
	}
}
