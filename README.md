## Lojinha API Automação
Esse é um repositório que contém a automação de alguns testes de API Rest de um software denominado Lojinha. Os sub-tópicos a seguir descrevem algumas decisões tomadas na estruturação do projeto.

## Tecnologias utilizadas

- Java
- JUnit
-  RestAssured
-  Maven

## Testes Automatizados
Testes para validar as partições de equivalência relacionadas ao valor do produto na Lojinha, que estão vinculados diretamente a regra de negócio que diz que o valor do produto deve estar entre 0,01 e 7.000,00

## Notas Gerais



Sempre utilizo a notação Before Each para o token que será   
utilizado posteriormente no s métodos de teste.  
Armazeno os dados que são enviados para a API através do uso de classes POJO.    
Crio dados iniciais através do uso de classe Data Factory para
facilitar a    criação e controle dos mesmos.
