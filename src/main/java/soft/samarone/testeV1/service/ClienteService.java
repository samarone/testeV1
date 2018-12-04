package soft.samarone.testeV1.service;

import static soft.samarone.testeV1.Constants.GEO_DEFAULT;
import static soft.samarone.testeV1.Constants.TEMP_MAX_DEFAULT;
import static soft.samarone.testeV1.Constants.TEMP_MIN_DEFAULT;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import soft.samarone.testeV1.domain.Cliente;
import soft.samarone.testeV1.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private final Logger LOG = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private GeolocalizacaoService geoService;

	@Autowired
	private PrevisaoService previsaoService;
	
    @Value("${ip.client.fixed:8.8.8.8}")
    private String ipClientFixed;
    
    @Value("${ip.loopback.fixed:127.0.0.1}")
    private String ipLoopback;
	
	public Cliente save(@Valid Cliente cliente) {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String ip = request.getRemoteAddr();
		
		if (ipLoopback.equals(ip)) {
			ip = ipClientFixed;
		}
		
		cliente.setIp(ip);
		
		cliente.setGeoLocalizacao(GEO_DEFAULT);
		cliente.setTempMin(TEMP_MIN_DEFAULT);
		cliente.setTempMax(TEMP_MAX_DEFAULT);
		cliente.setCriadoEm(LocalDateTime.now());
		
		cliente = clienteRepository.save(cliente);
		LOG.info("Cliente {} salvo com sucesso!", cliente);
		
		geoService.handleCliente(cliente, clienteRepository::save);
		previsaoService.handleCliente(cliente, clienteRepository::save);
		
		return cliente;
	}

	public Optional<Cliente> update(@Valid Cliente cliente) {
		
		Optional<Cliente> recuperado = clienteRepository.findById(cliente.getId());
		
		if (recuperado.isPresent()) {
			
			LOG.info("Cliente de ID: {} encontrado: {}", recuperado.get().getId(), recuperado.get());
			recuperado.get().setNome(cliente.getNome());
			recuperado.get().setIdade(cliente.getIdade());
			Cliente atualizado = clienteRepository.save(recuperado.get());
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
