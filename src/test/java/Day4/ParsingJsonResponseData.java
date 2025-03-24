package Day4;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJsonResponseData {

	// @Test
	void testJsonResponse() {

		// Approach 1
		Response res1 = given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/class")
				.then()
				.statusCode(200).header("Content-Type", "application/json").body("students[0].name", equalTo("Sundar"))
				.extract().response();

		// Print the response for debugging
		System.out.println("Response Body: " + res1.getBody().asString());

		// Approach 2
		Response res2 = given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/class");

		Assert.assertEquals(res2.getStatusCode(), 200);
		Assert.assertEquals(res2.header("Content-Type"), "application/json");

		String name = res2.jsonPath().get("students[0].name").toString();
		Assert.assertEquals(name, "Sundar");
	}

	@Test
	void testJsonResponseBodyData() {
		// Sending the GET request
		Response res = given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/class");

		// Parse the response body as JSON
		String response=res.getBody().asString();
		JSONObject jo = new JSONObject(response);
		
		 // Iterate over the 'students' array and print names
        System.out.println("List of Student Names:");
		for (int i = 0; i < jo.getJSONArray("students").length(); i++) {
			String sname = jo.getJSONArray("students").getJSONObject(i).get("name").toString();
			System.out.println(sname);
		}

		// search particular name
		boolean status = false;
		for (int i = 0; i < jo.getJSONArray("students").length(); i++) {
			String sname = jo.getJSONArray("students").getJSONObject(i).get("name").toString();
			if (sname.equalsIgnoreCase("sundar")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);

		// validate total marks of physics
		double totalprice = 0;
		for (int i = 0; i < jo.getJSONArray("students").length(); i++) {
			String price = jo.getJSONArray("students").getJSONObject(i).get("physics").toString();

			totalprice = totalprice + Double.parseDouble(price);
		}
		System.out.println("total marks of physics:" + totalprice);
		Assert.assertEquals(totalprice, 112.0);
	}
}
