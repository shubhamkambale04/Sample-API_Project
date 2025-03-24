package Day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;
import org.testng.annotations.Test;

public class FileUploadAndDownload {
	
	@Test(priority=1)
	void singleFileUpload()
	{
		 // Specify the file path
	    String filepath1 = "path/to/your/file1.txt"; // Replace with the actual file path
	    File myfile1 = new File(filepath1);
	    
		given()
			.multiPart("file", myfile1)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:3000/uploadFile")
		.then()
			.statusCode(200)
			.body("filename", equalTo("file1.txt"))
			.log().all();
	}
	
	@Test
	void multipleFileUpload()
	{
		 // Specify the file path
	    String filepath1 = "path/to/your/file1.txt"; // Replace with the actual file path
	    File myfile1 = new File(filepath1);
	    String filepath2 = "path/to/your/file2.txt";
	    File myfile2 = new File(filepath2);
	    
	    // for uploading multiple files(50,100) but it will not work for every API
	    //File filearr[]= {myfile1,myfile2};
	    
		given()
			.multiPart("files", myfile1)
			.multiPart("files", myfile2)
			//.multiPart("files", filearr)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:3000/uploadMultipleFiles")
		.then()
			.statusCode(200)
			.body("[0].filename", equalTo("file1.txt"))
			.body("[1].filename", equalTo("file2.txt"))
			.log().all();
	}
	
	@Test(priority=2)
	void fileDownload()
	{
		given()
			
		.when()
			.get("http://localhost:3000/downloadFile/file1.txt")
		.then()
			.statusCode(200)
			.log().body()
			.log().all();
	}

}
