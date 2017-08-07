package dao;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.entidades.Cliente;

public class ClienteDAO {
	
	private List<Cliente> clientesMock;

	public ClienteDAO(List<Cliente> clientesMock) {
		super();
		this.clientesMock = clientesMock;
	}

	public Cliente findById(final Long id) {
		Cliente clienteRetorno = null;
		for (final Cliente cliente : this.clientesMock) {
			if (cliente.getId().equals(id)) {
				clienteRetorno = cliente;
			}
		}
		return clienteRetorno;
	}

	public List<Cliente> findAll() {
		return this.clientesMock;
	}

}
