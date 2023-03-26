package tests.api.putDataValidation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.APIUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class PutRequestTest {

        @BeforeClass
        public void startUp() throws IOException {
        APIUtils.readAPIConfigs();
        APIUtils.setBaseURL();
    }
        @Feature("API Test")
        @Story("API-001: tests.api test for update user functionality")
        @Description("to test user is updated when update user tests.api is called")
        @Test(description = "to verify user is updated when update user tests.api is called")
        public void test_user_updated_successfully_using_put_api_method1_body_from_json_file() throws Exception {

            String end_point = APIUtils.api_config.getProperty("create_user_end_point");
            String body = Files.readString(Path.of(System.getProperty("user.dir") + "\\src\\test\\resources\\api_test_data\\CreateUserJsonBody.json"), StandardCharsets.US_ASCII);

            Response response = APIUtils.postRequest(end_point,body);
            System.out.println(response);

            Assert.assertEquals(response.getStatusCode(),201,"status code mismatch");

            String id =  response.getBody().jsonPath().getJsonObject("id").toString();

            System.out.println(id);

             end_point = APIUtils.api_config.getProperty("create_user_end_point")+"/"+id;
              body = Files.readString(Path.of(System.getProperty("user.dir") + "\\src\\test\\resources\\api_test_data\\updateUserJsonBody.json"), StandardCharsets.US_ASCII);

               response = APIUtils.putRequest(end_point,body);

               System.out.println(response);

        Assert.assertEquals(response.getStatusCode(),200,"status code mismatch");

       // String id =  response.getBody().jsonPath().getJsonObject("id").toString();
        String name =  response.getBody().jsonPath().getJsonObject("name").toString();
        String email =  response.getBody().jsonPath().getJsonObject("email").toString();
        String gender =  response.getBody().jsonPath().getJsonObject("gender").toString();
        String status =  response.getBody().jsonPath().getJsonObject("status").toString();

        JsonMapper mapper = new JsonMapper();
        JsonNode node = mapper.readValue(body, JsonNode.class);

        // method - 2
        Assert.assertEquals(name, node.get("name").asText(),"name mismatch");
        Assert.assertEquals(email, node.get("email").asText(),"email mismatch");
        Assert.assertEquals(gender, node.get("gender").asText(),"gender mismatch");
        Assert.assertEquals(status, node.get("status").asText(), "status mismatch");
    }
    }

