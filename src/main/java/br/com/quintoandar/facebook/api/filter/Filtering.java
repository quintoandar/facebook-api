package br.com.quintoandar.facebook.api.filter;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author akira
 *
 */
public class Filtering {
	private final String bla;
	
	public Filtering(List<Filter> f){
		ObjectMapper mapper = new ObjectMapper();
		String bla2;
		try {
			bla2 = mapper.writeValueAsString(f);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			bla2 = "";
		}
		bla = bla2;
	}
	
	@Override
	public String toString() {
		return bla;
	}
	
}
