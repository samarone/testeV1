package soft.samarone.testeV1.integration.metaweather.dto;

import java.util.ArrayList;
import java.util.List;

public class MetaWeatherResponseList {

	    private List<MetaWeatherResponse> results;
	 
	    public MetaWeatherResponseList() {
	    	results = new ArrayList<>();
	    }

		public List<MetaWeatherResponse> getResults() {
			return results;
		}

		public void setResults(List<MetaWeatherResponse> results) {
			this.results = results;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MetaWeatherResponseList [results=");
			builder.append(results);
			builder.append("]");
			return builder.toString();
		}
	    
	}
