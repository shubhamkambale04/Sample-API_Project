package Day6;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;

public class xmlSchemaValidation {

	//xml to xml schema converter
	//https://www.site24x7.com/tools/xml-to-xsd.html
	@Test
	void xmlSchema()
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler")
		.then()
			.assertThat().statusCode(200).body(RestAssuredMatchers.matchesXsdInClasspath("xmlschema.xsd"));
	}
}