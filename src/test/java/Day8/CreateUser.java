package Day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class CreateUser{
	
	static int id;
	@Test(priority = 1)
	void userCreation(ITestContext context) 
	{
		Faker faker=new Faker();

		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "Inactive");
		
		String bearerToken="Bearer 71fe8823f998f45516963b01ec72f2f06fa64a2367d687c38619e6fabba5c60c";
		
		//Response res=given()
		id=given()
			.contentType(ContentType.JSON)
			.header("Authorization", bearerToken)
			.body(data.toString())

		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		 
		 //System.out.println("Response: " + res.asString());
		 //int id = res.jsonPath().getInt("id");
		 System.out.println(id);
		 
		 // for all test class execution 
		 //context.setAttribute("userid", id);
		 
		// for each test class execution
		 context.getSuite().setAttribute("userid", id);

	}
	
}
