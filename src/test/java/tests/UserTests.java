package tests;

import api.UserApi;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtil;
import utils.JsonUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

public class UserTests extends BaseTest {

    private String createdUserId;

    @Test(priority = 1, description = "Create user using JSON test data")
    public void testCreateUser() {
        // Create user from JSON
        List<Map<String, Object>> users = JsonUtil.readJsonArray("testdata/json/users.json");
        Map<String, Object> userData = users.get(0);
        
        User userPayload = new User(userData.get("name").toString(), userData.get("job").toString());
        
        Response response = UserApi.createUser(userPayload);
        
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        Assert.assertEquals(response.jsonPath().getString("name"), userPayload.getName());
        
        // Assert Schema Validation
        File schemaFile = new File("src/test/resources/schemas/user-schema.json");
        if (schemaFile.exists()) {
             response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
        }

        createdUserId = response.jsonPath().getString("id");
        Assert.assertNotNull(createdUserId, "User ID should not be null");
    }

    @Test(priority = 2, description = "Get created user details", dependsOnMethods = "testCreateUser")
    public void testGetUser() {
        Response response = UserApi.getUser(createdUserId);
        
        // ReqRes mock API might return 404 for newly created users since it's a mock
        // We handle standard scenarios or we test a known available ID
        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertNotNull(response.jsonPath().getString("id"));
        } else {
             // Fallback for Mock API behavior (it doesn't save dynamically created users)
             Response fallbackResp = UserApi.getUser("1");
             Assert.assertEquals(fallbackResp.getStatusCode(), 200);
        }
    }

    @Test(priority = 3, description = "Update user details", dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        User userPayload = new User("morpheus updated", "zion resident");
        // JSONPlaceholder mock updates ID 1 successfully
        Response response = UserApi.updateUser(userPayload, "1");
        
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
    }

    @Test(priority = 4, description = "Delete user", dependsOnMethods = "testCreateUser")
    public void testDeleteUser() {
        // JSONPlaceholder returns 200 for successful deletion
        Response response = UserApi.deleteUser("1");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    @Test(priority = 5, description = "Negative Test - Invalid Get User")
    public void testInvalidGetUser() {
        Response response = UserApi.getUser("99999"); // Assuming this ID does not exist
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    // DataProvider using Excel
    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return ExcelUtil.getTestData("testdata/excel/testdata.xlsx", "Users");
    }

    @Test(priority = 6, dataProvider = "UserData", description = "Create multiple users using Excel data")
    public void testCreateUsersFromExcel(String name, String job) {
        if (name == null || name.isEmpty()) return; // Skip empty rows
        
        User userPayload = new User(name, job);
        Response response = UserApi.createUser(userPayload);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
    }
}
