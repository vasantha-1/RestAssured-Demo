package rest;

import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {
	public static void main(String[] args) {
		JsonPath js = new JsonPath(payload.CoursePrice());
		//print no of courses returned by api
		int coursesCount = js.getInt("courses.size()");
		System.out.println(coursesCount);
		// Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount); 
		//Print Title of the first course
		String firstCourseTitile = js.getString("courses[0].title");
		System.out.println(firstCourseTitile);
		//Print All course titles and their respective Prices
		
		for(int i=0; i<coursesCount ; i++) {
			String title = js.getString("courses["+i+"].title");
			int price = js.getInt("courses["+i+"].price");		
			System.out.println(title);
			System.out.println(price);
		}
		
		//Print no of copies sold by RPA Course
		for(int i=0; i<coursesCount ; i++) {
			String courseTitle = js.getString("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA")) {
				int copies = js.getInt("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		//Verify if Sum of all Course prices matches with Purchase Amount
		int sum=0;
		for(int i=0; i<coursesCount; i++) {
			int p = js.getInt("courses["+i+"].price");
			int c = js.getInt("courses["+i+"].copies");
			int amount = p*c;
			sum = sum+amount;
		}
		System.out.println(sum==purchaseAmount);
	}
}
