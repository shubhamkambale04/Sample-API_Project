package Day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HTTPRequest {

	static int id;

	@Test(priority = 1)
	void createUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Shubham");
		data.put("job", "QA Engineer");

		Response response = given().contentType(ContentType.JSON).body(data)
				.when().post("https://reqres.in/api/users");

		response.then()
		.statusCode(201)
		.body("name", equalTo("Shubham"))
		.body("job", equalTo("QA Engineer"))
		.log().all();

		id = response.jsonPath().getInt("id"); // Extracting ID
	}

	@Test(priority = 2)
	void getUsers() {
		if (id != 0) {
			given()
			.when().get("https://reqres.in/api/users?page=2")
			.then().statusCode(200).body("page", equalTo(2))
					.log().all();
		} else {
			System.out.println("Skipping update, as ID was not created.");
		}
	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	void updateUser() {
		if (id != 0) {
			HashMap<String, String> data = new HashMap<>();
			data.put("name", "Shubham");
			data.put("job", "QA Automation");

			given().contentType(ContentType.JSON).body(data)
			.when().put("https://reqres.in/api/users/" + id)
			.then()
					.statusCode(200).log().all();
		} else {
			System.out.println("Skipping update, as ID was not created.");
		}
	}

	@Test(priority = 4, dependsOnMethods = { "createUser" })
	void deleteUser() {
		if (id != 0) {
			given()
			.when().delete("https://reqres.in/api/users/" + id)
			.then().statusCode(204)
			.log().all();
		} else {
			System.out.println("Skipping delete, as ID was not created.");
		}
	}
}
