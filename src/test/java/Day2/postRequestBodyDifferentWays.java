package Day2;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class postRequestBodyDifferentWays {

    String id;

    // 1) Post request body creation using HashMap
    //@Test(priority = 1)
    void testPostHashMap() {
        HashMap data = new HashMap();
        data.put("name", "Shubham");
        data.put("gender", "Male");
        data.put("physics", 88);
        data.put("maths", 87);
        data.put("english", 78);

        // If an array is required
        // String[] courseArr = {"C", "C++"};
        // data.put("courses", courseArr);

        id = given().contentType(ContentType.JSON).body(data)
        		.when().post("http://localhost:3000/students")
        		.then().statusCode(201)
        			.body("name", equalTo("Shubham"))
        			.body("gender", equalTo("Male"))
        			.body("physics", equalTo(88))
        			.body("maths", equalTo(87))
        			.body("english", equalTo(78))
        			.header("Content-Type", equalTo("application/json"))
        			.log().all().extract().jsonPath().getString("id"); // Extract ID as String
    }

	// 2) Post request body creation using org.json
	//@Test(priority = 1)
	void testPostUsingJsonLibrary() {
		
		JSONObject data=new JSONObject();
		data.put("name", "Shubham");
        data.put("gender", "Male");
        data.put("physics", 88);
        data.put("maths", 87);
        data.put("english", 78);

		id =given().contentType(ContentType.JSON).body(data.toString())
				.when().post("http://localhost:3000/students")
				.then().statusCode(201)
	               .body("name", equalTo("Shubham"))
	               .body("gender", equalTo("Male"))
	               .body("physics", equalTo(88))
	               .body("maths", equalTo(87))
	               .body("english", equalTo(78))
	               .header("Content-Type", equalTo("application/json"))
	               .log().all().extract().jsonPath().getString("id");
	}
	
	// 3) Post request body creation using POJO class
	//@Test(priority = 1)
	void testPostUsingPOJO() {
		
		PojoPostRequest data = new PojoPostRequest();
		data.setName("Shubham");
		data.setGender("Male");
		data.setPhysics(88);
		data.setMaths(87);
		data.setEnglish(78);

		id =given().contentType(ContentType.JSON).body(data)
				.when().post("http://localhost:3000/students")
				.then().statusCode(201)
	               .body("name", equalTo("Shubham"))
	               .body("gender", equalTo("Male"))
	               .body("physics", equalTo(88))
	               .body("maths", equalTo(87))
	               .body("english", equalTo(78))
	               .header("Content-Type", equalTo("application/json"))
	               .log().all().extract().jsonPath().getString("id");
	}

	// 4) Post request body creation using json file data
	@Test(priority = 1)
	void testPostUsingJsonFileData() throws IOException {
		
		// Path to your JSON file
        String filePath = ".\\Body.json";
        // Read JSON file content into a String
        String data = new String(Files.readAllBytes(Paths.get(filePath)));
       
		id =given().contentType(ContentType.JSON).body(data)
				.when().post("http://localhost:3000/students")
				.then().statusCode(201)
	               .body("name", equalTo("Shubham"))
	               .body("gender", equalTo("Male"))
	               .body("physics", equalTo(88))
	               .body("maths", equalTo(87))
	               .body("english", equalTo(78))
	               .header("Content-Type", equalTo("application/json"))
	               .log().all().extract().jsonPath().getString("id");
	}
	@Test(priority = 2)
	void testDeleteJsonFileData() {
			given()
				.when().delete("http://localhost:3000/students/" + id)
				.then().statusCode(200).log().all();
	}
}
