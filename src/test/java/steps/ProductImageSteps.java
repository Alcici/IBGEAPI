package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ProductImageSteps {

    private Response response;
    private String apiUrl;
    private String token;

    @Given("que eu tenho um produto com userId {string}, source {string} e color {string}")
    public void que_eu_tenho_um_produto_com_userId_source_e_color(String userId, String source, String color) {
        this.apiUrl = String.format("https://www.advantageonlineshopping.com/catalog/api/v1/product/image/%s/%s/%s", userId, source, color);
        System.out.println("API URL: " + this.apiUrl);
    }

    @Given("eu obtenho um token de autenticação")
    public void eu_obtenho_um_token_de_autenticacao() {
        Response authResponse = given()
                .contentType("application/json")
                .body("{\"username\": \"seu_nome_de_usuario\", \"password\": \"sua_senha\"}")
                .when()
                .post("https://www.advantageonlineshopping.com/api/v1/authenticate");

        authResponse.then().statusCode(200);
        token = authResponse.jsonPath().getString("token");
        System.out.println("Token: " + token);
    }

    @When("eu envio uma requisição POST para {string} com a nova imagem")
    public void eu_envio_uma_requisicao_POST_para_a_URL_da_API_com_a_nova_imagem(String url) {
        System.out.println("Enviando requisição POST para: " + url);
        response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("multipart/form-data")
                .multiPart("file", "C:\\Users\\lucas.alcici\\OneDrive - Inmetrics\\Imagens\\fetchImage.jpg") // Substitua pelo caminho real da imagem
                .when()
                .post(url);
        System.out.println("Resposta: " + response.asString());
    }

    @Then("a resposta deve ter um status code {int}")
    public void a_resposta_deve_ter_um_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("a resposta deve conter um campo {string}")
    public void a_resposta_deve_conter_um_campo(String campo) {
        response.then().body(campo, not(empty()));
    }

    @Then("o campo {string} não deve ser vazio")
    public void o_campo_não_deve_ser_vazio(String campo) {
        response.then().body(campo, not(empty()));
    }
}