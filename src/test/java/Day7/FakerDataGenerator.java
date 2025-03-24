package Day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	@Test
	void testGenerateData() {
		
		Faker faker = new Faker();

		String name = faker.name().fullName(); 
		String firstName = faker.name().firstName(); 
		String lastName = faker.name().lastName(); 
		String streetAddress = faker.address().streetAddress();
		String username=faker.name().username();
		String password=faker.internet().password();
		String phno=faker.phoneNumber().phoneNumber();
		String email=faker.internet().emailAddress();
		String business=faker.business().creditCardNumber();
		
		System.out.println(name);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(streetAddress);
		System.out.println(username);
		System.out.println(password);
		System.out.println(phno);
		System.out.println(email);
		System.out.println(business);
			
	}
}
