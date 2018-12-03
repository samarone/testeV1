package soft.samarone.testeV1.integration.ipvigilante.dto;

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
@JsonPropertyOrder({ "ipv4", "continent_name", "country_name", "subdivision_1_name", "subdivision_2_name", "city_name",
		"latitude", "longitude" })
public class IpvigilanteResponseData {

	@JsonProperty("ipv4")
	private String ipv4;
	@JsonProperty("continent_name")
	private String continentName;
	@JsonProperty("country_name")
	private String countryName;
	@JsonProperty("subdivision_1_name")
	private String subdivision1Name;
	@JsonProperty("subdivision_2_name")
	private Object subdivision2Name;
	@JsonProperty("city_name")
	private String cityName;
	@JsonProperty("latitude")
	private String latitude;
	@JsonProperty("longitude")
	private String longitude;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("ipv4")
	public String getIpv4() {
		return ipv4;
	}

	@JsonProperty("ipv4")
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	@JsonProperty("continent_name")
	public String getContinentName() {
		return continentName;
	}

	@JsonProperty("continent_name")
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	@JsonProperty("country_name")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("country_name")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("subdivision_1_name")
	public String getSubdivision1Name() {
		return subdivision1Name;
	}

	@JsonProperty("subdivision_1_name")
	public void setSubdivision1Name(String subdivision1Name) {
		this.subdivision1Name = subdivision1Name;
	}

	@JsonProperty("subdivision_2_name")
	public Object getSubdivision2Name() {
		return subdivision2Name;
	}

	@JsonProperty("subdivision_2_name")
	public void setSubdivision2Name(Object subdivision2Name) {
		this.subdivision2Name = subdivision2Name;
	}

	@JsonProperty("city_name")
	public String getCityName() {
		return cityName;
	}

	@JsonProperty("city_name")
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JsonProperty("latitude")
	public String getLatitude() {
		return latitude;
	}

	@JsonProperty("latitude")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public String getLongitude() {
		return longitude;
	}

	@JsonProperty("longitude")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
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