package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.LogTransferencia;


public class LogTransferenciaDAO {

		private List<LogTransferencia> historicoTransferencia;

		public List<LogTransferencia> findAll() {
			return historicoTransferencia;
		}
		
		public void add(final LogTransferencia logTransferencia) {
			this.historicoTransferencia.add(logTransferencia);
		}

		public LogTransferenciaDAO() {
			super();
			this.historicoTransferencia = new ArrayList<LogTransferencia>();
		}
	}