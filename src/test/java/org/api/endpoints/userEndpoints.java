package org.api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.api.payloads.User;


public class userEndpoints {
    //userEndpoints is basically contains CRUD operations basically.

    public static Response createUser(User payload) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(routes.post_url);
        return response;

    }

    public static Response getUser(String userName) {

        Response response = given()
                .pathParams("username", userName)
                .when()
                .get(routes.get_url);
        return response;

    }

    public static Response updateUser(String userName, User payload) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParams("username", userName)
                .when()
                .put(routes.update_url);
        return response;

    }

    public static Response deleteUser(String userName) {

        Response response = given()
                .pathParams("username", userName)
                .delete(routes.delete_url);
        return response;

    }
}
