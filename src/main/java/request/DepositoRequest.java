package request;

import com.mkyong.entidades.ContaCorrente;

public class DepositoRequest {
	
	private ContaCorrente contaCorrente;
	private Double valor;

	public DepositoRequest(ContaCorrente contaCorrente, Double valor) {
		super();
		this.contaCorrente = contaCorrente;
		this.valor = valor;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
