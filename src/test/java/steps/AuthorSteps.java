package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class AuthorSteps {

    private Response response;
    //This is Get endpoint for Author details
    @Given("I hit the OpenLibrary author endpoint {string}")
    public void i_hit_the_api(String authorId) {
        String url = "https://openlibrary.org/authors/" + authorId + ".json";

        response = given()
                .when()
                .get(url);

        assertThat("Status code should be 200", response.getStatusCode(), is(200));
    }

    @Then("the personal_name should be {string}")
    public void verify_personal_name(String expectedName) {
        String actualName = response.jsonPath().getString("personal_name");
        assertThat(actualName, equalTo(expectedName));
    }

    @Then("the response should contain alternate name {string}")
    public void verify_alternate_name(String expectedAltName) {
        List<String> altNames = response.jsonPath().getList("alternate_names");
        assertThat(altNames, hasItem(expectedAltName));
    }
}
