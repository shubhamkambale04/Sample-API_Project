package Day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetUser {
	
	@Test(priority = 2)
	void userGet(ITestContext context) 
	{
		// this should come from createuser class
		// for all test class execution 
		//int id=(int) context.getAttribute("userid");
		// for all test class execution 
		int id=(int) context.getSuite().getAttribute("userid");
		
		String bearerToken="Bearer 71fe8823f998f45516963b01ec72f2f06fa64a2367d687c38619e6fabba5c60c";
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization", bearerToken)
			.pathParam("id", id)

		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")

		.then()
			.statusCode(200).log().all();
	}

}
