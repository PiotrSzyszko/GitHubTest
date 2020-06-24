package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;

@RunWith(Cucumber.class)
public class StepDefinition {

    private static RequestSpecification requestSpecification;
    private static Response response;
    private static String userName;
    private static String sha;

    @Given("^User \"([^\"]*)\" logs in$")
    public void user_something_logs_in(String strArg1) throws Throwable {
        userName = strArg1;
    }

    @When("^User creates repository named \"([^\"]*)\"$")
    public void user_creates_repository_named_something(String strArg1) throws Throwable {

        requestSpecification = given().log().all().baseUri("https://api.github.com/user/repos")
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=")
                .header("Content-Type", "application/json")
                .header("Cookie", "_octo=GH1.1.1887974940.1592602259; logged_in=no")
                .body("{\n" +
                        "  \"name\": \"" + strArg1 + "\",\n" +
                        "  \"description\": \"This is your first repository\",\n" +
                        "  \"homepage\": \"https://github.com\",\n" +
                        "  \"private\": true,\n" +
                        "  \"has_issues\": true,\n" +
                        "  \"has_projects\": true,\n" +
                        "  \"has_wiki\": true\n" +
                        "}");

        response = requestSpecification.when().post();
        // .then().assertThat().log().all().statusCode(201);
    }

    @Then("^Repository is created$")
    public void repository_is_created() throws Throwable {

        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.statusCode());
    }




    @When("^User pushes commit with message \"([^\"]*)\" into \"([^\"]*)\" repository$")
    public void user_pushes_commit_with_message_something_into_something_repository(String strArg1, String strArg2) throws Throwable {
        requestSpecification = given().log().all().baseUri("https://api.github.com/repos/" + userName + "/" + strArg2 + "/git/commits")
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=")
                .header("Content-Type", "application/json")
                .header("Cookie", "_octo=GH1.1.1887974940.1592602259; logged_in=no")
                .body("{\n" +
                        "  \"message\": \"" + strArg1 + "\",\n" +
                        "  \"author\": {\n" +
                        "    \"name\": \"Piotr Szyszko\",\n" +
                        "    \"email\": \"pszyszko@github.com\",\n" +
                        "    \"date\": \"2008-07-09T16:13:30+12:00\"\n" +
                        "  },\n" +
                        "  \"parents\": [\n" +
                        "    \"d815669a07b73db0a29688b3bd568b5e7b5c6fa4\"\n" +
                        "  ],\n" +
                        "  \"tree\": \"1c22bdaa8ffbe3bee1cc64acf1beefdd081c4397\",\n" +
                        "  \"signature\": \"-----BEGIN PGP SIGNATURE-----\\n\\niQIzBAABAQAdFiEESn/54jMNIrGSE6Tp6cQjvhfv7nAFAlnT71cACgkQ6cQjvhfv\\n7nCWwA//XVqBKWO0zF+bZl6pggvky3Oc2j1pNFuRWZ29LXpNuD5WUGXGG209B0hI\\nDkmcGk19ZKUTnEUJV2Xd0R7AW01S/YSub7OYcgBkI7qUE13FVHN5ln1KvH2all2n\\n2+JCV1HcJLEoTjqIFZSSu/sMdhkLQ9/NsmMAzpf/iIM0nQOyU4YRex9eD1bYj6nA\\nOQPIDdAuaTQj1gFPHYLzM4zJnCqGdRlg0sOM/zC5apBNzIwlgREatOYQSCfCKV7k\\nnrU34X8b9BzQaUx48Qa+Dmfn5KQ8dl27RNeWAqlkuWyv3pUauH9UeYW+KyuJeMkU\\n+NyHgAsWFaCFl23kCHThbLStMZOYEnGagrd0hnm1TPS4GJkV4wfYMwnI4KuSlHKB\\njHl3Js9vNzEUQipQJbgCgTiWvRJoK3ENwBTMVkKHaqT4x9U4Jk/XZB6Q8MA09ezJ\\n3QgiTjTAGcum9E9QiJqMYdWQPWkaBIRRz5cET6HPB48YNXAAUsfmuYsGrnVLYbG+\\nUpC6I97VybYHTy2O9XSGoaLeMI9CsFn38ycAxxbWagk5mhclNTP5mezIq6wKSwmr\\nX11FW3n1J23fWZn5HJMBsRnUCgzqzX3871IqLYHqRJ/bpZ4h20RhTyPj5c/z7QXp\\neSakNQMfbbMcljkha+ZMuVQX1K9aRlVqbmv3ZMWh+OijLYVU2bc=\\n=5Io4\\n-----END PGP SIGNATURE-----\\n\"\n" +
                        "}");

        response = requestSpecification.when().post();
    }

    @Then("^Commit is pushed to repository$")
    public void commit_is_pushed_to_repository() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.statusCode());
    }



    public void user_creates_file_content(String strArg1, String strArg2, String strArg3) throws Throwable {
        requestSpecification = given().log().all().baseUri("https://api.github.com/repos/" + userName + "/" + strArg2 + "/contents/" + strArg3)
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=")
                .header("Content-Type", "application/json")
                .header("Cookie", "_octo=GH1.1.1887974940.1592602259; logged_in=no")
                .body("{\n" +
                        "  \"message\": \"" + strArg1 + "\",\n" +
                        "  \"committer\": {\n" +
                        "    \"name\": \"Piotr Szyszko\",\n" +
                        "    \"email\": \"pszyszko@github.com\"\n" +
                        "  },\n" +
                        "  \"content\": \"bXkgbmV3IGZpbGUgY29udGVudHM=\"\n" +
                        "}");

        response = requestSpecification.when().put();
    }


    public void file_content_has_been_commited() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.statusCode());
        sha = response.jsonPath().get("commit.sha").toString();
    }




    @When("^User creates Pull Request on \"([^\"]*)\" repository from \"([^\"]*)\" branch to \"([^\"]*)\"$")
    public void user_creates_pull_request_on_something_repository_from_something_branch_to_something(
            String strArg1, String strArg2, String strArg3) throws Throwable {
        requestSpecification = given().log().all().baseUri("https://api.github.com/repos/" + userName + "/" + strArg1 + "/pulls")
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=")
                .header("Content-Type", "application/json")
                .header("Cookie", "_octo=GH1.1.1887974940.1592602259; logged_in=no")
                .body("{\n" +
                        "  \"title\": \"Amazing new feature\",\n" +
                        "  \"body\": \"Please pull these changes\",\n" +
                        "  \"head\": \"" + strArg2 + "\",\n" +
                        "  \"base\": \"" + strArg3 + "\"\n" +
                        "}");

        response = requestSpecification.when().post();
    }

    @Then("^Pull Request is created$")
    public void pull_request_is_created() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.statusCode());
    }




    @When("^User accepts Pull Request number \"([^\"]*)\" on \"([^\"]*)\" repository$")
    public void user_accepts_pull_request_number_something_on_something_repository(String strArg1, String strArg2) throws Throwable {
        requestSpecification = given().log().all()
                .baseUri("https://api.github.com/repos/" + userName + "/" + strArg2 + "/pulls/" + strArg1 + "/merge")
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=");

        response = requestSpecification.when().put();
    }

    @Then("^Pull Request is accepted$")
    public void pull_request_is_accepted() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals("Pull Request successfully merged", response.jsonPath().get("message").toString());
    }




    @When("^User deletes \"([^\"]*)\" repository$")
    public void user_deletes_something_repository(String strArg1) throws Throwable {
        requestSpecification = given().log().all().baseUri("https://api.github.com/repos/" + userName + "/" + strArg1 )
                .header("Authorization", "Basic cGlvdHIuc3p5c3prb0BnbWFpbC5jb206S3JlbWF0b3JpdW0jMDk=");

        response = requestSpecification.when().delete();
    }

    @Then("^Repository is deleted$")
    public void repository_is_deleted() throws Throwable {
        Assert.assertNotNull(response);
        Assert.assertEquals(204, response.statusCode());
    }


}
