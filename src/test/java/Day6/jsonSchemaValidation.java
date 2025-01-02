package Day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class jsonSchemaValidation {
	
	//json to json schema converter
	//https://jsonformatter.org/json-to-jsonschema
	
	@Test
	void jsonScheman()
	{
		given()
		
		.when()
			.get("http://localhost:3000/class")
		.then()
			.assertThat().statusCode(200)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemavalidation.json"));
	}
}
