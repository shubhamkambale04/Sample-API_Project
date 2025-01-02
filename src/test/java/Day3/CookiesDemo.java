package Day3;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo {
	
	//@Test(priority=1)
	void testCookies()
	{
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC","AZ6Zc-U15uvJhB4s2QX_yYBVpudGrLPxBFfcZyGnbZMgswIgIkRovYSwYQ")
			.log().all();
	}
	
	@Test(priority=2)
	void getCookiesInfo()
	{
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		// get single cookie
		String cookie_value=res.getCookie("AEC");
		System.out.println(cookie_value);
		
		// get all cookies info
		Map <String, String> cookie_values=res.getCookies();
		System.out.println(cookie_values.keySet());
		
		// Extracting and printing keys and values using a loop
        for (String key : cookie_values.keySet()) {
            // Get the value associated with the key
            String cookie_value1 = cookie_values.get(key);
            String cookie_value2 =res.getCookie(key);
            // Print the key and value
            System.out.println("Key: " + key + ", Value: " + cookie_value2);
        }
	}
}
