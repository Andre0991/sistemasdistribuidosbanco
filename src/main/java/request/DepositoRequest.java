package request;

import com.mkyong.entidades.ContaCorrente;

public class DepositoRequest {
	
	private String numero;
	private Double valor;


	public Double getValor() {
		return valor;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
