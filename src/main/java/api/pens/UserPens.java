package api.pens;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import api.user.User;

import static io.restassured.RestAssured.given;

public class UserPens {

    @Step("Создание пользователя")
    public Response createUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");
        return response;
    }

    @Step("Авторизация пользователя")
    public Response loginUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login");
        return response;
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        Response response = given()
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
        return response;
    }

}
