package api;

import io.restassured.response.Response;
import model.User;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "/auth/register");
    }

    public Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "/auth/login");
    }

    public Response deleteUser(User user) {
        String token = loginUser(user)
                .then()
                .extract()
                .path("accessToken");

        return given()
                .header("Authorization", token)
                .delete(BASE_URL + "/auth/user");
    }
}
