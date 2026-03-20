package api;

import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, Object payload) {
        return given(SpecBuilder.getRequestSpec())
                .body(payload)
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path) {
        return given(SpecBuilder.getRequestSpec())
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, Map<String, Object> queryParams) {
        return given(SpecBuilder.getRequestSpec())
                .queryParams(queryParams)
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String path, Object payload, String id) {
        return given(SpecBuilder.getRequestSpec())
                .pathParam("id", id)
                .body(payload)
                .when()
                .put(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response delete(String path, String id) {
        return given(SpecBuilder.getRequestSpec())
                .pathParam("id", id)
                .when()
                .delete(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }
}
