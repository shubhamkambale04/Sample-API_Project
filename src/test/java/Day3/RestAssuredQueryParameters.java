package Day3;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test; 

public class RestAssuredQueryParameters {
	
	@Test
	void testQueryAndPathParameters()
	{
		given()
			.pathParam("mypath", "users")  // path parameter
			.queryParam("page", 2).queryParam("id", 5) // query parameters
		
		.when()
			.get("https://reqres.in/api/{mypath}")
			
		.then()
			.statusCode(200).log().all();
		
	}
}
