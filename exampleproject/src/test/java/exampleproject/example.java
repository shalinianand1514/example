package exampleproject;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class example {
	
	//DataProvider 
	@DataProvider(name="provider")
	public Object[][] data(){
		Object[][] swagerData=new Object[2][8];
		swagerData[0][0]="1";
		swagerData[0][1]="sha15";
		swagerData[0][2]="shalini";
		swagerData[0][3]="anandhan";
		swagerData[0][4]="shalu12@gmail.com";
		swagerData[0][5]="passs2123";
		swagerData[0][6]="978789076";
		swagerData[0][7]="1";
		swagerData[1][0]="2";
		swagerData[1][1]="shinny67";
		swagerData[1][2]="shinny";
		swagerData[1][3]="kutty";
		swagerData[1][4]="shinny@gmail.com";
		swagerData[1][5]="123434567";
		swagerData[1][6]="8220908706";
		swagerData[1][7]="2";
	
		return swagerData;
	}
	
	//Create the user using the data provider
	@SuppressWarnings("unchecked")
	@Test(enabled=true,priority=1,dataProvider="provider")
	public void createUser(String id,String un,String fn,String ln,String email,String pass,String ph,String us){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
		JSONObject obj =new JSONObject();
		
		obj.put("id",id);
		obj.put("username",un);
		obj.put("firstName",fn);
		obj.put("lastName",ln);
		obj.put("email",email);
		obj.put("password",pass);
		obj.put("phone",ph);
		obj.put("userstatus",us);
		
		
		given().contentType(ContentType.JSON)
		       .body(obj.toJSONString()).
		 when().post("/user")
		.then().statusCode(200).log().all();
		
	}
	
	//Get user data 
	@Test(enabled=true,priority=2)
	public void getUser(){
		
		RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
		given().get("/user/sha15").
		then()
		.statusCode(200).log().all();
		
		
		given().get("/user/shinny67").
		then()
		.statusCode(200).log().all();
	}
	
	//Login into the account using the dataProvider
	@Test(enabled=true,priority=3,dataProvider="provider")
	public void LoginUser(String id,String un,String fn,String ln,String email,String pass,String ph,String us){
       RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		
	
	given().queryParam("username", un)
	.queryParam("password", pass).
	when()
	  .get("/user/login").
	then()
	   .statusCode(200)
	   .log().all();
		
		
	}
	
	//Update the user using the put method
	@SuppressWarnings("unchecked")
	@Test(enabled=true,priority=4)
	public void UpdateUser(){
		 RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		 
		 
		 JSONObject obj=new JSONObject();
			
		 obj.put("id","1");
			obj.put("username","sha15");
			obj.put("firstName","Shalini");
			obj.put("lastName","anandhan");
			obj.put("email","shalu12@gmail.com");
			obj.put("password","passs2123");
			obj.put("phone","978789076");
			obj.put("userstatus","1");
			
			given().contentType(ContentType.JSON)
			   .body(obj.toJSONString())
			.when()
			   .put("/user/sha15")
			   .then()
			   .statusCode(200).log().all();
	}
	
	//delete the user
	@Test(enabled=true,priority=5)
	public void deleteUser(){
		 RestAssured.baseURI="https://petstore.swagger.io/v2"; 
		 
		 given().delete("/user/sha15").then().statusCode(200).log().all();
		 given().delete("/user/shinny67").then().statusCode(200).log().all();
	}

}