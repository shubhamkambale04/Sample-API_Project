package Day8;

import org.json.JSONObject;
import com.github.javafaker.Faker;


public class APIChaining {
	
	public Faker faker;
	public JSONObject data;
	
	// post--> pre request data-->post-->post request(set variable)--->
	//get--->post request validate--->
	//put--->pre request data--->
	//delete--->post request(unset variable)
	public APIChaining()
	{
	faker=new Faker();

	data=new JSONObject();
	data.put("name", faker.name().fullName());
	data.put("gender", "Male");
	data.put("email", faker.internet().emailAddress());
	data.put("status", "Inactive");
	
	String bearerToken="Bearer 71fe8823f998f45516963b01ec72f2f06fa64a2367d687c38619e6fabba5c60c";
	System.out.println(bearerToken);

	
	}
}
