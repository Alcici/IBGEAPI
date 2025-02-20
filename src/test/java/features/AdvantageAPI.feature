Feature: advantageonlineshopping API

  Scenario: Search for a specific product
    Given I have the product search API endpoint
    When I search for a product with name "laptop"
    Then I should see the product details in the response


  Scenario: Atualizar a imagem de um produto

    Given que eu tenho um produto com userId "12345", source "camera" e color "red"
    And eu obtenho um token de autenticação
    When eu envio uma requisição POST para "https://www.advantageonlineshopping.com/catalog/api/v1/product/image/12345/camera/red" com a nova imagem
    Then a resposta deve ter um status code 200
    And a resposta deve conter um campo "imageId"
    And o campo "imageId" não deve ser vazio