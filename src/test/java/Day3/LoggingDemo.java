package Day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class LoggingDemo {
	
	@Test(priority=1)
	void testlogs()
	{
		given()
		
		.when()
			.get("https://www.google.com/")
			
		.then()
			.log().all()
			.log().body()
			.log().cookies()
			.log().headers();
	}

}
