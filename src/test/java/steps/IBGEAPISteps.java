package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;

public class IBGEAPISteps {

    private IBGEAPI api;
    private Response response;
    private int codigoMunicipio;
    private int codigoEstado;

    @Given("que eu tenho o código do município {int}")
    public void que_eu_tenho_o_codigo_do_municipio(int codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
        api = new IBGEAPI();
    }

    @Given("que eu tenho o código do estado {int}")
    public void que_eu_tenho_o_codigo_do_estado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
        api = new IBGEAPI();
    }

    @When("eu solicitar a população do município")
    public void eu_solicitar_a_populacao_do_municipio() {
        response = RestAssured.get("https://servicodados.ibge.gov.br/api/v1/localidades/municipios/" + codigoMunicipio);
    }

    @When("eu solicitar o PIB do estado")
    public void eu_solicitar_o_pib_do_estado() {
        response = RestAssured.get("https://servicodados.ibge.gov.br/api/v3/agregados/5938/periodos/2010/variaveis/37?localidades=N3[" + codigoEstado + "]");
    }

    @When("eu solicitar os dados geográficos do estado")
    public void eu_solicitar_os_dados_geograficos_do_estado() {
        response = RestAssured.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + codigoEstado);
    }

    @When("eu solicitar a taxa de alfabetização do estado")
    public void eu_solicitar_a_taxa_de_alfabetizacao_do_estado() {
        // Exemplo fictício, pois a API do IBGE não fornece diretamente a taxa de alfabetização
        response = null;
    }

    @When("eu solicitar a taxa de mortalidade do estado")
    public void eu_solicitar_a_taxa_de_mortalidade_do_estado() {
        // Exemplo fictício, pois a API do IBGE não fornece diretamente a taxa de mortalidade
        response = null;
    }

    @Then("a resposta deve conter o nome do município e a região")
    public void a_resposta_deve_conter_o_nome_do_municipio_e_a_regiao() {
        String municipio = response.jsonPath().getString("nome");
        String regiao = response.jsonPath().getString("microrregiao.mesorregiao.UF.regiao.nome");
        assertNotNull(municipio);
        assertNotNull(regiao);
    }

    @Then("a resposta deve conter o nome do estado e o PIB")
    public void a_resposta_deve_conter_o_nome_do_estado_e_o_pib() {
        String estado = response.jsonPath().getString("resultados[0].series[0].localidade.nome");
        String pib = response.jsonPath().getString("resultados[0].series[0].serie.2010");
        assertNotNull(estado);
        assertNotNull(pib);
    }

    @Then("a resposta deve conter o nome do estado e a região")
    public void a_resposta_deve_conter_o_nome_do_estado_e_a_regiao() {
        String estado = response.jsonPath().getString("nome");
        String regiao = response.jsonPath().getString("regiao.nome");
        assertNotNull(estado);
        assertNotNull(regiao);
    }

    @Then("a resposta deve indicar que os dados não estão disponíveis")
    public void a_resposta_deve_indicar_que_os_dados_nao_estao_disponiveis() {
        assertNull(response);
    }
}