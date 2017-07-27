package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.ContaCorrente;
import com.mkyong.entidades.ContaPoupanca;

public class ClienteDAO {
	
	private List<Cliente> clientesMock;

	public ClienteDAO() {
		super();
		this.clientesMock = new ArrayList<Cliente>();

		// client 1
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Andre");
		cliente1.setSobrenome("PT");
		cliente1.setId(1L);
		ContaCorrente contaCorrente1 = new ContaCorrente();
		contaCorrente1.setCliente(cliente1);
		contaCorrente1.setNumeracao("BBBBB");
		contaCorrente1.setSaldo(10D);
		cliente1.setContaCorrente(contaCorrente1);
		ContaPoupanca contaPoupanca1 = new ContaPoupanca();
		contaPoupanca1.setCliente(cliente1);
		contaPoupanca1.setNumeracao("BBBBB");
		contaPoupanca1.setSaldo(10D);
		cliente1.setContaPoupanca(contaPoupanca1);
		
		// client 2
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Iasmin");
		cliente2.setSobrenome("P");
		cliente2.setId(2L);
		ContaCorrente contaCorrente2 = new ContaCorrente();
		contaCorrente2.setCliente(cliente2);
		contaCorrente2.setNumeracao("BBBBB");
		contaCorrente2.setSaldo(20D);
		cliente2.setContaCorrente(contaCorrente2);
		ContaPoupanca contaPoupanca2 = new ContaPoupanca();
		contaPoupanca2.setCliente(cliente2);
		contaPoupanca2.setNumeracao("BBBBB");
		contaPoupanca2.setSaldo(20D);
		cliente2.setContaPoupanca(contaPoupanca2);

		// client 3
		Cliente cliente3 = new Cliente();
		cliente3.setNome("Leonardo");
		cliente3.setSobrenome("F");
		cliente3.setId(3L);
		ContaCorrente contaCorrente3 = new ContaCorrente();
		contaCorrente3.setCliente(cliente3);
		contaCorrente3.setNumeracao("CCCCC");
		contaCorrente3.setSaldo(30D);
		cliente3.setContaCorrente(contaCorrente3);
		ContaPoupanca contaPoupanca3 = new ContaPoupanca();
		contaPoupanca3.setCliente(cliente3);
		contaPoupanca3.setNumeracao("CCCCC");
		contaPoupanca3.setSaldo(30D);
		cliente3.setContaPoupanca(contaPoupanca3);

		this.clientesMock.add(cliente1);
		this.clientesMock.add(cliente2);
		this.clientesMock.add(cliente3);
	}
	
	public Cliente findById(Long id) {
		for (Cliente cliente : this.clientesMock) {
			if (cliente.getId().equals(id)) {
				return cliente;
			}
		}
		return null;
	}

}
