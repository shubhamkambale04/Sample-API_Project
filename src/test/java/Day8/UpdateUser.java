package Day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class UpdateUser {
	

	@Test(priority = 3)
	void userUpdate(ITestContext context) 
	{
		// this should come from createuser class
		// for all test class execution 
		//int id=(int) context.getAttribute("userid");
		// for all test class execution 
		int id=(int) context.getSuite().getAttribute("userid");
		
		Faker faker=new Faker();

		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken="Bearer 71fe8823f998f45516963b01ec72f2f06fa64a2367d687c38619e6fabba5c60c";
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization", bearerToken)
			.body(data.toString())
			.pathParam("id", id)

		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")

		.then()
			.statusCode(200).log().all();
	}

}
