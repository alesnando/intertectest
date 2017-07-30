package cr.intertec.userapp.json.implementacion;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cr.intertec.userapp.json.IJsonTransformer;
import cr.intertec.userapp.presentacion.ResponseService;
import cr.intertec.userapp.presentacion.Result;

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

	@Override
	public Object extraer(BufferedReader br, ResponseService<Result> response) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(br, new TypeReference<ResponseService<Result>>() {});
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		//return null;
	}

}
