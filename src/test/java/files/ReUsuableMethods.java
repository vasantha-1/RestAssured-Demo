package files;

import io.restassured.path.json.JsonPath;

public class ReUsuableMethods {
	public static JsonPath rawToJson(String response) {
		JsonPath jsr =  new JsonPath(response);
		return jsr;
	}
}