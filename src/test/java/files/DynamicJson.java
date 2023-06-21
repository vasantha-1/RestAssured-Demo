 package files;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import files.payload;
import static io.restassured.RestAssured.*;
public class DynamicJson {
	@Test
	public void addBook() {
		RestAssured.baseURI="http://216.10.245.166";
		 String Resp = given().log().all().header("Content-Type","application/json")
		.body(payload.AddBook())
		.when().post("/Library/Addbook.php")
		.then().contentType(ContentType.JSON).assertThat().log().all().statusCode(200)
		.extract().response().asString();
		 System.out.println(Resp);
		 JsonPath js = ReUsuableMethods.rawToJson(Resp);
		 String id = js.get("ID");
		 System.out.println(id);
	}
}

