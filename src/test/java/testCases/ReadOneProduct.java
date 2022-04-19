       package testCases;

       import org.testng.annotations.Test;
       import org.testng.asserts.SoftAssert;
       import io.restassured.path.json.JsonPath;
       import io.restassured.response.Response;
       import static io.restassured.RestAssured.*;

       public class ReadOneProduct {
	   SoftAssert SoftAssert;

	   public ReadOneProduct() {
		SoftAssert = new SoftAssert();
	   }

	   @Test
        public void readOneProduct() {
		Response response = given().baseUri("https://techfios.com/api-prod/api/product")
		.header("Content-Type", "application/json").header("Authorization", "LKJFKJLK544BLJLKBM4")
		.queryParam("id", "4221")
		.when()
		.get("/read_one.php")
		.then().extract()
		.response();

		int actualResponseStatus = response.getStatusCode();
		System.out.println("actual Response Status: " + actualResponseStatus);
		SoftAssert.assertEquals(actualResponseStatus, 200, "Status codes are not matching!");

		String actualResponseContentType = response.getHeader("Content-Type");
		System.out.println("actual Response ContentType: " + actualResponseContentType);
		SoftAssert.assertEquals(actualResponseContentType, "application/json");

		String actualResponseBody = response.getBody().asString();
		System.out.println("actualResponseBody: " + actualResponseBody);

		JsonPath jp = new JsonPath(actualResponseBody);
		String productId = jp.get("id");
		SoftAssert.assertEquals(productId, "4221", "Product Id not matching!");

		String productName = jp.get("name");
		SoftAssert.assertEquals(productName, "RAUL's Products available now", "Product names are not matching!");

		String productPrice = jp.get("price");
		SoftAssert.assertEquals(productPrice, "300", "Product Prices are not matching!");
		System.out.println("product Price: " + productPrice);
		SoftAssert.assertAll();
	}
}
