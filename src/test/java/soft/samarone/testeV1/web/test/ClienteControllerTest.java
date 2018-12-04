package soft.samarone.testeV1.web.test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soft.samarone.testeV1.domain.Cliente;
import soft.samarone.testeV1.repository.ClienteRepository;
import soft.samarone.testeV1.service.GeolocalizacaoService;
import soft.samarone.testeV1.service.PrevisaoService;
import soft.samarone.testeV1.web.rest.ClienteController;
import soft.samarone.testeV1.web.rest.dto.ClienteDTO;

@AutoConfigureRestDocs
@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

	@MockBean
	ClienteRepository repository;

	@MockBean
	private GeolocalizacaoService geoService;

	@MockBean
	private PrevisaoService previsaoService;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		Cliente cliente = new Cliente("Meu Nome", 25, "187.116.92.240", "-23.50000,-47.45960", 15.0, 35.0,
				LocalDateTime.now());
		cliente.setId(1L);
		
		List<Cliente> list = new ArrayList<Cliente>(1);
		list.add(cliente);
		
		Page<Cliente> page = new PageImpl<Cliente>(list);
		
		when(repository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
		when(repository.findById(1L)).thenReturn(Optional.of(cliente));
		when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
	}

	@Test
	public void criandoCliente() throws JsonProcessingException, Exception {
		ClienteDTO clienteDTO = new ClienteDTO("Meu Nome", 25);
		mockMvc.perform(post("/api/clientes")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(clienteDTO)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome", is(equalTo("Meu Nome"))))
                .andExpect(jsonPath("$.idade", is(equalTo(25))))
				.andDo(document("{method-name}/",
						requestFields(fieldWithPath("id").description("Id do Cliente").ignored(),
								fieldWithPath("nome").description("Nome do Cliente"),
								fieldWithPath("idade").description("Idade do Cliente"))));
	}
	
	@Test
	public void atualizaCliente() throws JsonProcessingException, Exception {
		
		ClienteDTO clienteDTO = new ClienteDTO("Meu Novo Nome", 30);
		clienteDTO.setId(1L);
		
		mockMvc.perform(put("/api/clientes")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(clienteDTO)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome", is(equalTo("Meu Novo Nome"))))
                .andExpect(jsonPath("$.idade", is(equalTo(30))))
				.andDo(document("{method-name}/",
						requestFields(fieldWithPath("id").description("Id do Cliente").ignored(),
								fieldWithPath("nome").description("Nome do Cliente"),
								fieldWithPath("idade").description("Idade do Cliente"))));
	}
	
	@Test
	public void removeCliente() throws JsonProcessingException, Exception {
		this.mockMvc.perform(delete("/api/clientes/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		        .andExpect(jsonPath("$.nome", is(equalTo("Meu Nome"))))
		        .andExpect(jsonPath("$.idade", is(equalTo(25))))
				.andDo(document("{method-name}/",
						responseFields(fieldWithPath("id").description("Id do Cliente"),
								fieldWithPath("nome").description("Nome do CLiente"),
								fieldWithPath("idade").description("Idade do Cliente")),
						pathParameters(parameterWithName("id").description("Id do Cliente"))));
	}
	
	@Test
	public void findByIdCliente() throws JsonProcessingException, Exception {
		this.mockMvc.perform(get("/api/clientes/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andDo(document("{method-name}/",
				responseFields(fieldWithPath("id").description("Id do Cliente"),
						fieldWithPath("nome").description("Nome do CLiente"),
						fieldWithPath("idade").description("Idade do Cliente")),
				pathParameters(parameterWithName("id").description("Id do Cliente"))));
	}
	
	@Test
	public void listCliente() throws JsonProcessingException, Exception {
		this.mockMvc.perform(get("/api/clientes?page=0&size=10").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("{method-name}/",
						responseFields(fieldWithPath("[].id").description("Id do Cliente"),
								fieldWithPath("[].nome").description("Nome do CLiente"),
								fieldWithPath("[].idade").description("Idade do Cliente"))));
	}
	
	@TestConfiguration
	static class CustomizationConfiguration implements RestDocsMockMvcConfigurationCustomizer {
	    @Override
	    public void customize(MockMvcRestDocumentationConfigurer configurer) {
	        configurer.operationPreprocessors()
	            .withRequestDefaults(prettyPrint())
	            .withResponseDefaults(prettyPrint());
	    }
	    @Bean
	    public RestDocumentationResultHandler restDocumentation() {
	        return MockMvcRestDocumentation.document("{method-name}");
	    }
	}
}
