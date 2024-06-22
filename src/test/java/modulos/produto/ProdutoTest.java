package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de produto")

public class ProdutoTest {

    private String token;

    @BeforeEach
    public void beforeEach() {
        //Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        //port  = 8080 (pode ter que colocar a porta)
        basePath = "/lojinha-bugada";

        //Obter o token do usuário admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
        .when()
                .post("/v2/login")
        .then()
                .extract()
                .path("data.token");


    }
    @Test
    @DisplayName("Validar os limites zerado proibido do valor do produto")
    public void testValidarLimiteZeradoProibidoValorProduto() {

        //Rentar acessar com valo 0.00 e verificar que vem a mensagem de erro e o
        // status code retornado foi 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIguala(0.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);



    }
    @Test
    @DisplayName("Validar os limites maior 7000")
    public void testValidarLimitesmaior7000ValorProduto() {

        //Rentar acessar com valo 7000,01 e verificar que vem a mensagem de erro e o
        // status code retornado foi 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIguala(7000.01))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);



    }
}
