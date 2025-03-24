package Day3;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	// @Test(priority=1)
	void testHeaders() {
		given()

				.when().get("https://www.google.com/")

				.then().header("Content-Type", "text/html; charset=ISO-8859-1").and()
				.header("Contenty-Encoding", "gzip").and().header("Server", "gws").log().headers();
	}

	@Test(priority = 2)
	void getHeaders() {
		Response res = given().when().get("https://www.google.com/");

		// get single header info
		String headervalue = res.getHeader("Content-Type");
		System.out.println(headervalue);

		// get all headers info
		Headers myheaders = res.getHeaders();

		for (Header hd : myheaders) {
			String name = hd.getName();
			System.out.println(name);
			String value = hd.getValue();
			System.out.println(value);
		}

	}
}
