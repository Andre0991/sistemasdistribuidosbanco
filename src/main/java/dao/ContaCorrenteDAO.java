package dao;

import java.util.List;

import com.mkyong.entidades.ContaCorrente;

public class ContaCorrenteDAO {
	
	List<ContaCorrente> contasMock;
	
	public ContaCorrenteDAO(List<ContaCorrente> contasMock) {
		super();
		this.contasMock = contasMock;
	}

	public ContaCorrente findByNumero(final String numero) {
		ContaCorrente contaRetorno = null;
		for (ContaCorrente conta : this.contasMock) {
			if (conta.getNumeracao().equals(numero)) {
				contaRetorno = conta;
			}
		}
		return contaRetorno;
	}

}
