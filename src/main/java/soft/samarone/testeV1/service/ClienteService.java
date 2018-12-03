package soft.samarone.testeV1.service;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import soft.samarone.testeV1.domain.Cliente;
import soft.samarone.testeV1.repository.ClienteRepository;

@Service
public class ClienteService {

	private final Logger LOG = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente save(@Valid Cliente novoCliente) {
		clienteRepository.save(novoCliente);
		LOG.info("Cliente {} salvo com sucesso!");
		return novoCliente;
	}

	public Optional<Cliente> update(@Valid Cliente cliente) {
		Optional<Cliente> recuperado = clienteRepository.findById(cliente.getId());
		if (recuperado.isPresent()) {
			LOG.info("Cliente de ID: {} encontrado: {}", recuperado.get().getId(), recuperado.get());
			Cliente atualizado = clienteRepository.save(cliente);
			LOG.info("Cliente {} atualizado com sucesso!", atualizado);
			return Optional.of(atualizado);
		}
		return Optional.empty();
	}

	public Page<Cliente> findAll(Pageable pageable) {
		LOG.info("Listando todos os clientes com paginação!");
		return clienteRepository.findAll(pageable);
	}

	public Optional<Cliente> findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			LOG.info("Cliente de ID: {} encontrado: {}", id, cliente.get());
			return cliente;
		}

		LOG.warn("Cliente de ID: " + id + " não encontrado!");
		return Optional.empty();
	}

	public Optional<Cliente> delete(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			LOG.info("Removendo cliente {}!", cliente.get());
			clienteRepository.delete(cliente.get());
			return cliente;
		}

		LOG.warn("Cliente de ID: {} não encontrado!", id);
		return Optional.empty();
	}

}
