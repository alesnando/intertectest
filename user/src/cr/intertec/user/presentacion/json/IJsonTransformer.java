package cr.intertec.user.presentacion.json;

/**
 * 
 * Definition of methods for managment json
 *
 */
public interface IJsonTransformer {

	String toJson(Object data);

	Object fromJson(String json, Class clazz);

}
