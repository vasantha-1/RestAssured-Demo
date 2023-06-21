package Demo;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
/*  
  given()
  	content type,set cookies,add auth, add param,set headers info etc.....
  when()
  	get,post,put,delete
  then()
  validate status code. extract response, extract headers cookies & response body....
 */
public class HTTPRequests {
	int id;
	@Test(priority=1)
	void listUsers() {
		given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.body("page", equalTo(2)).log().all();
	}
	@Test(priority=2)
	void ceate() {
		id = 
		given()
		.contentType("application/json")
		.body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}")
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
	}
	@Test(priority=3, dependsOnMethods= {"create"})
	void update() {
		given()
		.contentType("application/json")
		.body("{\r\n"
				+ "    \"name\": \"ram\",\r\n"
				+ "    \"job\": \"teacher\"\r\n"
				+ "}")
		.when()
		.put("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(200).log().all();
	}
	@Test(priority=4)
	void delete() {
		given()
		.when()
		.delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204)
		.log().all();
	}
	
}















