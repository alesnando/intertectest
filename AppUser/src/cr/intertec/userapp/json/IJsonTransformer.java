package cr.intertec.userapp.json;

import java.io.BufferedReader;

import cr.intertec.userapp.presentacion.ResponseService;
import cr.intertec.userapp.presentacion.Result;

/**
 * 
 * Definition of methods for managment json
 *
 */
public interface IJsonTransformer {

	String toJson(Object data);

	Object fromJson(String json, Class clazz);
	
	Object extraer(BufferedReader br, ResponseService<Result> response);

}
