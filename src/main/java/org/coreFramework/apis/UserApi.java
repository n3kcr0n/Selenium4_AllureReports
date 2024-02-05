package org.coreFramework.apis;


import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.coreFramework.models.TestUser;
import org.coreFramework.utils.ConfigUtils;

import static io.restassured.RestAssured.given;


public class UserApi {
    //Singleton
    private static UserApi userApi;
    private UserApi(){
    }
    public static UserApi getInstance(){
        if(userApi == null){
            userApi = new UserApi();
        }
        return userApi;
    }

    //Methods
    @Step("Request to register using API")
    public Response registerUserByApi(TestUser user){
        String baseUrl = ConfigUtils.getInstance().getAppUrl();
        String apiType = ConfigUtils.getInstance().getApiType();
        String apiVersion = ConfigUtils.getInstance().getApiVersion();
        String path = ConfigUtils.getInstance().getApiUserPath()+ConfigUtils.getInstance().getApiRegisterPath();
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(apiType+apiVersion+path)
                .then()
                .extract().response();
    }
}
