import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.runner.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends ParentClass{

    JsonPath jsonObject ;
    String bID;

    @Test(priority = 0)
    public void createBoard() {


        createBoard("NewCreateBoard");
        System.out.println(response.prettyPrint());
        Assert.assertEquals("NewCreateBoard", response.jsonPath().get("name"));
        bID = response.jsonPath().get("idOrganization");
        System.out.println(bID);
    }

    @Test(priority = 1)
    public void createCard() {

        Response response1 = createCardRequest(bID ,"NewCreateCard1");
        System.out.println(response1.prettyPrint());
        //jsonObject = response.jsonPath();
        //Assert.assertEquals("NewCreateCard1", response1.jsonPath().get("name"));


        Response response2 = createCardRequest(bID,"NewCreateCard2");

        //Assert.assertEquals("NewCreateCard2", response2.jsonPath().get("name"));

    }


    @Test(priority = 2)
    public void update(){
        update("updateCardName",random(cardID.size()));
        jsonObject = response.jsonPath();
        Assert.assertEquals("updateCardName", jsonObject.get("name"));
    }
    @Test(priority = 3)
    public void delete(){

        cardDelete(random(cardID.size()));
        jsonObject = response.jsonPath();
        Assert.assertEquals("deleteCardName", jsonObject.get("name"));
    }
    @Test(priority = 4)
    public void deleteBoard(){

        boardDelete();
        jsonObject = response.jsonPath();
        Assert.assertEquals("deleteBoardName", jsonObject.get("name"));
    }
}
