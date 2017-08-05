package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContaCorrenteDTO {

	private Long id;

	private Double saldoContaCorrente;

	public Long getId() {
		return ido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSaldoContaCorrente() {
		return saldoContaCorrente;
	}

	public void setSaldoContaCorrente(Double saldoContaCorrente) {
		this.saldoContaCorrente = saldoContaCorrente;
	}

}
