package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SearchProductSteps {

    private Response response;

    @Given("I have the product search API endpoint")
    public void i_have_the_product_search_api_endpoint() {
        RestAssured.baseURI = "https://www.advantageonlineshopping.com/catalog/api/v1/products/search";
    }

    @When("I search for a product with name {string}")
    public void i_search_for_a_product_with_name(String productName) {
        response = given()
                .queryParam("name", productName)
                .when()
                .get();
    }

    @Then("I should see the product details in the response")
    public void i_should_see_the_product_in_the_response() {
        response.then().statusCode(200)
                .body("products", not(empty()));
    }

    @Then("I print the response")
    public void i_print_the_response() {
        response.prettyPrint();
    }
}