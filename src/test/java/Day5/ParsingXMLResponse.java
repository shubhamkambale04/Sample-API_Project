package Day5;

import static io.restassured.RestAssured.given;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	
	//@Test
	void testXMLResponse()
	{
		/*
		 * //Approach 1
		 * 
		 * given()
		 * 
		 * .when() .get("http://restapi.adequateshop.com/api/Traveler") .then()
		 * .statusCode(200) .header("Content-Type", "applicationm/xml") .body("xpath",
		 * equalTo("1"));
		 */
		
		
		  //Approach 2
		  
		  Response res=given()
		  
		  .when() .get("http://restapi.adequateshop.com/api/Traveler");
		  
		  Assert.assertEquals(res.getStatusCode(), 200);
		 
		
	}
	
	@Test
	void testXMLResponseBody()
	{
		Response res= given()
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler");
				XmlPath xml=new XmlPath(res.asString());
				 List<String> travellers=xml.getList("xmlpath");
				 Assert.assertEquals(travellers.size(), 10);
				 
				 //verify total traveller names
				 List<String> traveller_names=xml.getList("xmlpath");
				 
				 boolean status=false;
				 for(String traveller_name:traveller_names )
				 {
					 if(traveller_name.equalsIgnoreCase("vinod sharma"))
					 {
						 status=true;
						 break;
					 }
					 Assert.assertEquals(status, true);
				 }
	}

}
