Feature: Testar a API do IBGE

  Scenario: Obter a população de um município
    Given que eu tenho o código do município 3550308
    When eu solicitar a população do município
    Then a resposta deve conter o nome do município e a região

  Scenario: Obter o PIB de um estado
    Given que eu tenho o código do estado 35
    When eu solicitar o PIB do estado
    Then a resposta deve conter o nome do estado e o PIB

  Scenario: Obter dados geográficos de um estado
    Given que eu tenho o código do estado 35
    When eu solicitar os dados geográficos do estado
    Then a resposta deve conter o nome do estado e a região

  Scenario: Obter a taxa de alfabetização de um estado
    Given que eu tenho o código do estado 35
    When eu solicitar a taxa de alfabetização do estado
    Then a resposta deve indicar que os dados não estão disponíveis

  Scenario: Obter a taxa de mortalidade de um estado
    Given que eu tenho o código do estado 35
    When eu solicitar a taxa de mortalidade do estado
    Then a resposta deve indicar que os dados não estão disponíveis