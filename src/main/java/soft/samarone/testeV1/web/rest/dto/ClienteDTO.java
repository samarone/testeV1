package soft.samarone.testeV1.web.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteDTO {
	
	private Long id;

	@NotNull
	@Size(max = 100)
	private String nome;

	@NotNull
	private Integer idade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
}
