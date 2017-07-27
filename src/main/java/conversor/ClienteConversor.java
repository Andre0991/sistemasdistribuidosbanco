package conversor;

import com.mkyong.entidades.Cliente;

import dto.ContaCorrenteDTO;

public class ClienteConversor {

	public ContaCorrenteDTO convertClienteToContaCorrenteDTO(Cliente cliente) {
		ContaCorrenteDTO contaCorrenteDTO = new ContaCorrenteDTO();
		contaCorrenteDTO.setSaldoContaCorrente(cliente.getContaCorrente().getSaldo());
		contaCorrenteDTO.setId(cliente.getId());
		return contaCorrenteDTO;
	}

}
