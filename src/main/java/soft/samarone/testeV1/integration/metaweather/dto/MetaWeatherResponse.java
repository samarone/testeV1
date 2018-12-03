package soft.samarone.testeV1.integration.metaweather.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "distance", "title", "location_type", "woeid", "latt_long" })
public class MetaWeatherResponse {

	@JsonProperty("distance")
	private Integer distance;
	@JsonProperty("title")
	private String title;
	@JsonProperty("location_type")
	private String locationType;
	@JsonProperty("woeid")
	private Integer woeid;
	@JsonProperty("latt_long")
	private String lattLong;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("distance")
	public Integer getDistance() {
		return distance;
	}

	@JsonProperty("distance")
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("location_type")
	public String getLocationType() {
		return locationType;
	}

	@JsonProperty("location_type")
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	@JsonProperty("woeid")
	public Integer getWoeid() {
		return woeid;
	}

	@JsonProperty("woeid")
	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}

	@JsonProperty("latt_long")
	public String getLattLong() {
		return lattLong;
	}

	@JsonProperty("latt_long")
	public void setLattLong(String lattLong) {
		this.lattLong = lattLong;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
