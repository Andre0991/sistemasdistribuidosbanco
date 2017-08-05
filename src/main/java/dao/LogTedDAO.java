package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.LogTed;

public class LogTedDAO {

	private List<LogTed> historicoTeds;

	public List<LogTed> findAll() {
		return historicoTeds;
	}
	
	public void add(final LogTed logTed) {
		this.historicoTeds.add(logTed);
	}

	public LogTedDAO() {
		super();
		this.historicoTeds = new ArrayList<LogTed>();
	}

}
