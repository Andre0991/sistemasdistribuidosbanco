package request;

import com.mkyong.entidades.ContaCorrente;

public class DepositoRequest {
	
	private String numeroConta;
	private Double valor;


	public Double getValor() {
		return valor;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numero) {
		this.numeroConta = numero;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
