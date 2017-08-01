package request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TedRequest {
	
	private int idBancoDestino;
	private double valor;
	private String numeroContaOrigem;
	private String numeroContaDestino;

	public int getIdBancoDestino() {
		return idBancoDestino;
	}
	public void setIdBancoDestino(int idBanco) {
		this.idBancoDestino = idBanco;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getNumeroContaOrigem() {
		return numeroContaOrigem;
	}
	public void setNumeroContaOrigem(String numeroContaOrigem) {
		this.numeroContaOrigem = numeroContaOrigem;
	}
	public String getNumeroContaDestino() {
		return numeroContaDestino;
	}
	public void setNumeroContaDestino(String numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

}
