package org.coreFramework.apis;



import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.coreFramework.models.TestUser;
import org.coreFramework.utils.ConfigUtils;

import static io.restassured.RestAssured.given;

public class TodoApi {
    //Singleton
    private static TodoApi todoApi;
    private TodoApi(){

    }
    public static TodoApi getInstance(){
        if(todoApi==null){
            todoApi = new TodoApi();
        }
        return todoApi;
    }
    @Step("Request to add to do by API")
    public void addToDoTask(TestUser user, String item){
        String baseUrl = ConfigUtils.getInstance().getAppUrl();
        String apiType = ConfigUtils.getInstance().getApiType();
        String apiVersion = ConfigUtils.getInstance().getApiVersion();
        String path = ConfigUtils.getInstance().getApiTaskPath();
        Response res = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body("{\"item\":\""+item+"\", \"isCompleted\": false}")
                .auth().oauth2(user.getAccessToken())
                .when()
                .post(apiType+apiVersion+path)
                .then()
                .extract().response();
    }

}
