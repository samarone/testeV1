package soft.samarone.testeV1.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "TB_CLIENTES")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CD_SEQ_ID")
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(name = "NM_NOME", length = 100, nullable = false)
	private String nome;

	@NotNull
	@Column(name = "NR_IDADE", length = 3, nullable = false)
	private Integer idade;

	@NotNull
	@Size(max = 50)
	@Column(name = "NM_IP", length = 50, nullable = false)
	private String ip;

	@NotNull
	@Column(name = "NM_GEOLOCALIZACAO", length = 256, nullable = false)
	private String geoLocalizacao;

	@NotNull
	@Column(name = "NR_TEMP_MIN", length = 3, nullable = false)
	private Double tempMin;

	@NotNull
	@Column(name = "NR_TEMP_MAX", length = 3, nullable = false)
	private Double tempMax;

	@NotNull
	@Column(name = "TS_CRIADO_EM", nullable = false)
	private LocalDateTime criadoEm;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getGeoLocalizacao() {
		return geoLocalizacao;
	}

	public void setGeoLocalizacao(String geoLocalizacao) {
		this.geoLocalizacao = geoLocalizacao;
	}

	public Double getTempMin() {
		return tempMin;
	}

	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	public Double getTempMax() {
		return tempMax;
	}

	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idade == null) ? 0 : idade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (idade == null) {
			if (other.idade != null)
				return false;
		} else if (!idade.equals(other.idade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", idade=");
		builder.append(idade);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", geoLocalizacao=");
		builder.append(geoLocalizacao);
		builder.append(", tempMin=");
		builder.append(tempMin);
		builder.append(", tempMax=");
		builder.append(tempMax);
		builder.append(", criadoEm=");
		builder.append(criadoEm);
		builder.append("]");
		return builder.toString();
	}

}
