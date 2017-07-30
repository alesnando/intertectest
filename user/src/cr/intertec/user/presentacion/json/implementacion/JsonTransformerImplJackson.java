package cr.intertec.user.presentacion.json.implementacion;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cr.intertec.user.presentacion.json.IJsonTransformer;

/**
 * 
 *  Implementation of methods for managment json
 *
 */
public class JsonTransformerImplJackson implements IJsonTransformer {

	@Override
	public String toJson(Object data) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			return objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Object fromJson(String json, Class clazz) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			return objectMapper.readValue(json, clazz);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
