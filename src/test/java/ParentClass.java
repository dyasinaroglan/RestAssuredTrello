import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ParentClass {

    public static RequestSpecification request;
    public static Response response;
    public static String boardID;
    public static List<String> cardID = new ArrayList();


    public static final String key = "45882a4d291da73ea2c5a0d78deaa742";
    public static final String token = "6f664266ebb85e01d2d8fe99341fcdfc4436de9f13c3eab78df16dfdac4f8d6f";



    public void createBoard(String boardName) {

        JSONObject pathParam = new JSONObject();
        pathParam.put("key", key);
        pathParam.put("token", token);
        pathParam.put("name", boardName);

        response = RestAssured.given()
                .queryParam("key", key).queryParam("token", token)
                .queryParam("name", boardName)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                //.body(pathParam.toString())
                .when().post("https://api.trello.com/1/boards/")
                .then().extract().response();


        /*
        JSONObject pathParam = new JSONObject();
        pathParam.put("key",key);
        pathParam.put("token",token);
        pathParam.put("name",boardName);

        xxx = RestAssured.given()
                .header("Content-type","application/json")
                .body(pathParam.toString());

        response = xxx.request(Method.POST,"/boards/");
        boardID = response.jsonPath().getString("id");
    }

         */


    }

    public Response createCardRequest(String boardID, String cardName) {
        JSONObject pathParam = new JSONObject();
        pathParam.put("key", key);
        pathParam.put("token", token);
        pathParam.put("name", cardName);
        pathParam.put("idList", boardID);
        System.out.println(boardID);
        response = RestAssured.given()
                .queryParam("key", key).queryParam("token", token)
                .queryParam("name", cardName)
                .queryParam("idList", boardID)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
              //  .body(pathParam.toString())
                .when().post("https://api.trello.com/1/cards/")
                .then().statusCode(200).extract().response();
        return response;
    }

    public void update(String cardName, int size) {
        String id = cardID.get(size);

        JSONObject pathParam = new JSONObject();
        pathParam.put("key", key);
        pathParam.put("token", token);
        pathParam.put("name", cardName);

        response = RestAssured.given()
                .queryParam("key", key, "token", token, "name", cardName)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        //        .body(pathParam.toString())
                .when().put("https://api.trello.com/1/cards/" + id);

    }
    public void cardDelete(int num){

        String id = cardID.get(num);

        response = RestAssured.given()
                .queryParam("key", key, "token", token)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().delete("https://api.trello.com/1/cards/" + id);

    }
    public void boardDelete(){
        response = RestAssured.given()
                .queryParam("key", key, "token", token)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().delete("https://api.trello.com/1/boards/" + boardID);
    }

    public static int random(int size) {
        return (int) (Math.random() * size);
    }
}
