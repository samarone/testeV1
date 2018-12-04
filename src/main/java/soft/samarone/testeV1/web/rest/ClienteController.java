package soft.samarone.testeV1.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import soft.samarone.testeV1.domain.Cliente;
import soft.samarone.testeV1.service.ClienteService;
import soft.samarone.testeV1.web.rest.dto.ClienteDTO;
import soft.samarone.testeV1.web.rest.util.HeaderUtil;
import soft.samarone.testeV1.web.rest.util.PaginationUtil;
import soft.samarone.testeV1.web.rest.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class ClienteController {

	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/clientes")
	public ResponseEntity<ClienteDTO> criaCliente(@Valid @RequestBody ClienteDTO novoClienteDTO)
			throws URISyntaxException {
		LOG.info("REST request para salvar Cliente : {}", novoClienteDTO);

		Cliente cliente = convertFrom(novoClienteDTO);

		Cliente resultEntity = clienteService.save(cliente);

		ClienteDTO resultDTO = convertFrom(resultEntity);

		return ResponseEntity.created(new URI("/api/clientes/" + resultDTO.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("CLIENTE", resultDTO.getId().toString())).body(resultDTO);
	}

	@PutMapping("/clientes")
	public ResponseEntity<ClienteDTO> atualizaCliente(@Valid @RequestBody ClienteDTO alteradoClienteDTO)
			throws URISyntaxException {
		LOG.info("REST request para Atualizar Cliente : {}", alteradoClienteDTO);

		Cliente cliente = convertFrom(alteradoClienteDTO);

		Optional<Cliente> result = clienteService.update(cliente);

		Optional<ClienteDTO> resultDTO = convertFrom(result);

		return ResponseUtil.wrapOrNotFound(resultDTO,
				HeaderUtil.createEntityUpdateAlert("CLIENTE", alteradoClienteDTO.getId().toString()));
	}

	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteDTO>> listaClientes(@RequestParam("page") int pageIndex,
			@RequestParam("size") int pageSize) {
		LOG.info("REST request para recuperar todos os clientes com paginação");
		Page<Cliente> page = clienteService.findAll(PageRequest.of(pageIndex, pageSize));

		List<ClienteDTO> dtoList = page.getContent().stream().map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getIdade()))
				.collect(Collectors.toList());

		Page<ClienteDTO> dtoPage = new PageImpl<ClienteDTO>(dtoList);

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(dtoPage, "/api/clientes");
		return new ResponseEntity<>(dtoPage.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteDTO> recuperaCliente(@PathVariable Long id) {
		LOG.info("REST request que recupera Cliente de ID: {}", id);
		Optional<Cliente> result = clienteService.findById(id);
		
		Optional<ClienteDTO> resultDTO = convertFrom(result);
		
		return ResponseUtil.wrapOrNotFound(resultDTO);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<ClienteDTO> removeCliente(@PathVariable Long id) {
		LOG.info("REST request que deleta Cliente de ID: {}", id);
		Optional<Cliente> result = clienteService.delete(id);
		
		Optional<ClienteDTO> resultDTO = convertFrom(result);
		
		return ResponseUtil.wrapOrNotFound(resultDTO, HeaderUtil.createEntityDeletionAlert("CLIENTE", id.toString()));
	}

	
	// UTIL
	private ClienteDTO convertFrom(Cliente cliente) {
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setIdade(cliente.getIdade());
		return clienteDTO;
	}

	private Cliente convertFrom(ClienteDTO clienteDTO) {
		
		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setIdade(clienteDTO.getIdade());
		return cliente;
	}
	
	private Optional<ClienteDTO> convertFrom(Optional<Cliente> cliente) {
		
		if (cliente.isPresent()) {
			return Optional.of(convertFrom(cliente.get()));
		}
		
		return Optional.empty();
	}

}
