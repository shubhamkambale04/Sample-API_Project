package Day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDeserialization {

	// convert pojo to json
	@Test
	void serialization() throws JsonProcessingException {
		
		//created java object using pojo class
		pojo pj = new pojo();    
		pj.setName("Shubham");
		pj.setGender("Male");
		pj.setPhysics(88);
		pj.setMaths(87);
		pj.setEnglish(78);
		
		//convert java object to json object =serialization
		ObjectMapper objmapper=new ObjectMapper();
		String jsonData=objmapper.writerWithDefaultPrettyPrinter().writeValueAsString(pj);
		System.out.println(jsonData);
	}

	// convert json to pojo
	@Test
	void deserialization() throws JsonProcessingException {
		
		//created json object using
		String jsonData="{\r\n"
				+ "				  \"name\" : \"Shubham\",\r\n"
				+ "				  \"gender\" : \"Male\",\r\n"
				+ "				  \"physics\" : 88,\r\n"
				+ "				  \"maths\" : 87,\r\n"
				+ "				  \"english\" : 78\r\n"
				+ "				}";
		
		//convert json object to java object =deserialization
		ObjectMapper objmapper=new ObjectMapper();
		pojo pj= objmapper.readValue(jsonData, pojo.class);

		pj.getEnglish();
		pj.getGender();
		pj.getMaths();
		pj.getName();
		pj.getPhysics();
		
		System.out.println(pj.getEnglish());
		System.out.println(pj.getGender());
		System.out.println(pj.getMaths());
		System.out.println(pj.getName());
		System.out.println(pj.getPhysics());
	}
}
