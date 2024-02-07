package api.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import api.user.User;
import static constants.Constants.*;
import static io.restassured.RestAssured.given;

public class UserApi {

    @Step("Создание пользователя")
    public Response createUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(REGISTERED);
        return response;
    }

    @Step("Авторизация пользователя")
    public Response loginUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN);
        return response;
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        Response response = given()
                .header("Authorization", accessToken)
                .when()
                .delete(AUTHORIZATION);
        return response;
    }

}
