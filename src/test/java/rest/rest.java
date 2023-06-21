package rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.net.*;

import org.testng.Assert;
import files.ReUsuableMethods;

import files.payload;
public class rest {
	public static void main(String[] args) throws IOException {
			RestAssured.baseURI=("https://rahulshettyacademy.com");	
			String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
			.body(payload.AddPlace())
			.when().post("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
			System.out.println(response);
			
			JsonPath js = new JsonPath(response); //for parsing json
			String placeId=js.getString("place_id");
			System.out.println(placeId);
			
		//update place
			String newAddress = "70 winter walk, Russia";
			given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+placeId+"\",\r\n"
					+ "\"address\":\""+newAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ " ")
			.when().put("maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200);
		//get place
			
			String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id",placeId)
			.when().get("maps/api/place/add/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
			System.out.println(getPlaceResponse);
			JsonPath jsr = ReUsuableMethods.rawToJson(getPlaceResponse);
			String actualAddress = jsr.getString("address");
			System.out.println(actualAddress);
			//CucumberJunit & TestNG frameworks
			Assert.assertEquals(actualAddress, newAddress);
			 
			 
	}
}
