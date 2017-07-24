package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Cliente;

public class ClienteDAO {
	
	private List<Cliente> clientesMock;

	public ClienteDAO() {
		super();
		this.clientesMock = new ArrayList<Cliente>();
		
		// client 1
		Cliente cliente1 = new Cliente("Andre", "PT", 1L, 10D, 20D);
		Cliente cliente2 = new Cliente("Iasmin", "P", 2L, 30D, 50D);
		Cliente cliente3 = new Cliente("Leonardo", "F", 3L, 30D, 50D);
		
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
