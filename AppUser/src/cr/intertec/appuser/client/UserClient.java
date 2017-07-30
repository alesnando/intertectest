package cr.intertec.appuser.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import cr.intertec.appuser.dominio.User;
import cr.intertec.userapp.json.implementacion.JsonTransformerImplJackson;
import cr.intertec.userapp.presentacion.ResponseService;
import cr.intertec.userapp.presentacion.Result;
import cr.intertec.userapp.util.CodeStatus;

/**
 * 
 * Class that consumes the rest service
 *
 */
public class UserClient {

	/**
	 * Method to invoke the service
	 * @param user
	 * @return
	 */
	public ResponseService<Result> checkUsername(User user){
		ResponseService<Result> responseService = new ResponseService<Result>(CodeStatus.OK, new Result());
		BufferedReader br = null;
		JsonTransformerImplJackson transformer = new JsonTransformerImplJackson();
		String json = transformer.toJson(user);
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, json);
		Request request = new Request.Builder()
				  .url("http://localhost:8080/user/api/v1/user")
				  .post(body)
				  .addHeader("content-type", "application/json")
				  .build();

		Response response;
		try {
			response = client.newCall(request).execute();
			br = new BufferedReader(new InputStreamReader(response.body().byteStream()));
			responseService = (ResponseService<Result>) transformer.extraer(br, responseService);
		} catch (IOException e) {
			Logger.getLogger(UserClient.class.getName()).log(Level.SEVERE, null, e);
		}
		return responseService;
	}
}
