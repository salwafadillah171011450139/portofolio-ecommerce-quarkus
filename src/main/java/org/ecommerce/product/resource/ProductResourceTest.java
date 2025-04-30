package org.ecommerce.product.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testCreateProduct() {
        String body = """
        {
          "name": "Keyboard",
          "description": "Mechanical RGB",
          "price": "300000",
          "stock": "20",
          "category": "Elektronik"
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/products")
                .then()
                .statusCode(201);
    }

    @Test
    public void testGetAllProducts() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }
}
