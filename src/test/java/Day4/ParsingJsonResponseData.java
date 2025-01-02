package Day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJsonResponseData {

	// @Test
	void testJsonResponse() {

		/*
		 * // Approach 1
		 * 
		 * given() .contentType(ContentType.JSON)
		 * 
		 * .when() .get("http://localhost:3000/class")
		 * 
		 * .then() .statusCode(200).header("Content-Type", "application/json")
		 * .body("students[0].name", equalTo("Sundar"));
		 * 
		 */

		// Approach 2

		Response res = given().contentType(ContentType.JSON)

				.when().get("http://localhost:3000/class");

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");

		String name = res.jsonPath().get("students[0].name").toString();
		Assert.assertEquals(name, "Sundar");
	}

	@Test
	void testJsonResponseBodyData() {
	    // Sending the GET request
	    Response res = given().contentType(ContentType.JSON)
	            .when().get("http://localhost:3000/class");

	    // Parse the response body as JSON
	    JSONObject jo = new JSONObject(res.asString());
	    // Iterate over the 'students' array
	    for (int i = 0; i < jo.getJSONArray("students").length(); i++) {
	        String sname = jo.getJSONArray("students").getJSONObject(i).get("name").toString();
	        System.out.println(sname);
	    }
	    
	    //search particular name
	    boolean status=false;
	    for (int i = 0; i < jo.getJSONArray("students").length(); i++) {
	        String sname = jo.getJSONArray("students").getJSONObject(i).get("name").toString();
	        System.out.println(sname);
	        if(sname.equalsIgnoreCase("sundar"))
	        {
	        	status=true;
	        	break;
	        }
	        Assert.assertEquals(status, true);
	     }
	    
	    // validate total marks of physics
	    double totalprice=0;
	    for (int i = 0; i < jo.getJSONArray("students").length(); i++) {
	        String price = jo.getJSONArray("students").getJSONObject(i).get("physics").toString();
	        
	        totalprice=totalprice+Double.parseDouble(price);
	    }
	    System.out.println("total marks of physics:"+totalprice);
	    Assert.assertEquals(totalprice, 112.0);
	}
}
