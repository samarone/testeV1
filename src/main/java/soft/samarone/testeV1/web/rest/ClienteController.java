package soft.samarone.testeV1.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public ResponseEntity<Cliente> criaCliente(@Valid @RequestBody Cliente novoCliente) throws URISyntaxException {
		LOG.info("REST request para salvar Cliente : {}", novoCliente);

		Cliente result = clienteService.save(novoCliente);

		return ResponseEntity.created(new URI("/api/clientes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("CLIENTE", result.getId().toString())).body(result);
	}

	@PutMapping("/clientes")
	public ResponseEntity<Cliente> atualizaCliente(@Valid @RequestBody Cliente cliente) throws URISyntaxException {
		LOG.info("REST request para Atualizar Cliente : {}", cliente);

		Optional<Cliente> result = clienteService.update(cliente);
		return ResponseUtil.wrapOrNotFound(result,
				HeaderUtil.createEntityUpdateAlert("CLIENTE", cliente.getId().toString()));
	}

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> listaClientes(@RequestParam("page") int pageIndex,
			@RequestParam("size") int pageSize) {
		LOG.info("REST request para recuperar todos os clientes com paginação");
		Page<Cliente> page = clienteService.findAll(PageRequest.of(pageIndex, pageSize));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clientes");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> recuperaCliente(@PathVariable Long id) {
		LOG.info("REST request que recupera Cliente de ID: {}", id);
		Optional<Cliente> cliente = clienteService.findById(id);
		return ResponseUtil.wrapOrNotFound(cliente);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Cliente> removeCliente(@PathVariable Long id) {
		LOG.info("REST request que deleta Cliente de ID: {}", id);
		Optional<Cliente> result = clienteService.delete(id);
		return ResponseUtil.wrapOrNotFound(result,
				HeaderUtil.createEntityUpdateAlert("CLIENTE", id.toString()));
	}

}
