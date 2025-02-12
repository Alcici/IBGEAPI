package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class IBGEAPI {

    public static void main(String[] args) {
        IBGEAPI api = new IBGEAPI();
        api.getPopulacaoMunicipio(3550308); // Exemplo para São Paulo
        api.getPIBEstado(35); // Exemplo para São Paulo
        api.getDadosGeograficos(35); // Exemplo para São Paulo
        api.getTaxaAlfabetizacao(35); // Exemplo para São Paulo
        api.getTaxaMortalidade(35); // Exemplo para São Paulo
    }

    public void getPopulacaoMunicipio(int codigoMunicipio) {
        try {
            Response response = RestAssured.get("https://servicodados.ibge.gov.br/api/v1/localidades/municipios/" + codigoMunicipio);
            String municipio = response.jsonPath().getString("nome");
            String regiao = response.jsonPath().getString("microrregiao.mesorregiao.UF.regiao.nome");
            System.out.println("Município: " + municipio);
            System.out.println("Região: " + regiao);
        } catch (Exception e) {
            System.out.println("Erro ao obter dados de população: " + e.getMessage());
        }
    }

    public void getPIBEstado(int codigoEstado) {
        try {
            Response response = RestAssured.get("https://servicodados.ibge.gov.br/api/v3/agregados/5938/periodos/2010/variaveis/37?localidades=N3[" + codigoEstado + "]");
            String estado = response.jsonPath().getString("resultados[0].series[0].localidade.nome");
            String pib = response.jsonPath().getString("resultados[0].series[0].serie.2010");
            System.out.println("Estado: " + estado);
            System.out.println("PIB: " + pib);
        } catch (Exception e) {
            System.out.println("Erro ao obter dados de PIB: " + e.getMessage());
        }
    }

    public void getDadosGeograficos(int codigoEstado) {
        try {
            Response response = RestAssured.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + codigoEstado);
            String estado = response.jsonPath().getString("nome");
            String regiao = response.jsonPath().getString("regiao.nome");
            System.out.println("Estado: " + estado);
            System.out.println("Região: " + regiao);
        } catch (Exception e) {
            System.out.println("Erro ao obter dados geográficos: " + e.getMessage());
        }
    }

    public void getTaxaAlfabetizacao(int codigoEstado) {
        // Exemplo fictício, pois a API do IBGE não fornece diretamente a taxa de alfabetização
        System.out.println("Estado: " + codigoEstado);
        System.out.println("Taxa de Alfabetização: Dados não disponíveis diretamente na API do IBGE");
    }

    public void getTaxaMortalidade(int codigoEstado) {
        // Exemplo fictício, pois a API do IBGE não fornece diretamente a taxa de mortalidade
        System.out.println("Estado: " + codigoEstado);
        System.out.println("Taxa de Mortalidade: Dados não disponíveis diretamente na API do IBGE");
    }
}