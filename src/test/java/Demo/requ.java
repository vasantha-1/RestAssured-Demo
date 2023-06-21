package Demo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class requ {
	int id;
	@Test(priority=1)
	void getUser() {
		given()	
		.when()
		.get("https://reqres.in/api/users?page=2")//get request doesnot have prerequists.
		.then()
		.statusCode(200)
		.body("page",equalTo(2))
		.log().all();
	}
	@Test(priority=2)
	void createUser() {
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
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void update() {
				given()
				.contentType("application/json")
				.body("{\r\n"
						+ "    \"name\": \"allen\",\r\n"
						+ "    \"job\": \"teacher\"\r\n"
						+ "}")
				.when()
				.put("https://reqres.in/api/users/"+id)
				.then()
				.statusCode(200)
				.log().all();
				
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
