import io.restassured.RestAssured;
import org.junit.Before;

public class Uri {

    @Before
    public void before(){

        RestAssured.baseURI = "https://trello.com/1/";
    }
}
