package Day7;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class Authentication {
	
	@Test(priority=1)
	void testBasicAuthentication()
	{
		given()
			.contentType(ContentType.JSON).auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200).body("Authenticated", equalTo(true)).log().all();
	}
	
	@Test(priority=2)
	void testDigestAuthentication()
	{
		given()
			.contentType(ContentType.JSON).auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200).body("Authenticated", equalTo(true)).log().all();
	}
	
	@Test(priority=3)
	void testPreemptiveAuthentication()
	{
		given()
			.contentType(ContentType.JSON).auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200).body("Authenticated", equalTo(true)).log().all();
	}
	
	@Test(priority=4)
	void testBearerTokenAuthentication()
	{
		String bearerToken="Bearer ghp-24pH0Icz1PKHC1qOtLwj57AuDYmtSz2fuYKP";
		
		given()
			.contentType(ContentType.JSON).headers("Authentication", bearerToken)
		
		.when()
			.get("https://api-github.com/user/repos")
		
		.then()
			.statusCode(200).log().all();
	}
	
	@Test(priority=5)
	void testOuth1Authentication()
	{
		given()
			.contentType(ContentType.JSON).auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
		
		.when()
			.get("url")
		
		.then()
			.statusCode(200).log().all();
	}
	
	@Test(priority=6)
	void testOuth2Authentication()
	{
		given()
			.contentType(ContentType.JSON).auth().oauth2("token")
		
		.when()
			.get("url")
		
		.then()
			.statusCode(200).log().all();
	}
	
	@Test(priority=7)
	void testAPIKeyAuthentication()
	{
		// Method 1
		given()
			.contentType(ContentType.JSON).queryParams("appid", "fe9c5cddb7e01d747b4911c3fc9eaf2c")
		
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		
		.then()
			.statusCode(200).log().all();
		
		//Method 2
		
		given()
			.contentType(ContentType.JSON).queryParams("appid", "fe9c5cddb7e01d747b4911c3fc9eaf2c")
			.pathParam("mypath", "data/2.5/forecast/daily")
			.queryParam("q", "")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
			
		.when()
			.get("https://api.openweathermap.org/{mypath}")
			
		.then()
			.statusCode(200).log().all();
	}

}
