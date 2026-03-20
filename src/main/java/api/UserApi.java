package api;

import io.restassured.response.Response;
import models.User;
import utils.LoggerUtil;

import java.util.Map;

public class UserApi {

    public static Response createUser(User user) {
        LoggerUtil.info("Creating a new user: " + user.getName());
        return RestResource.post(Routes.USERS, user);
    }

    public static Response getUser(String id) {
        LoggerUtil.info("Fetching user with ID: " + id);
        return RestResource.get(Routes.USER_BY_ID.replace("{id}", id));
    }
    
    public static Response getUsers(Map<String, Object> queryParams) {
        LoggerUtil.info("Fetching users with query params");
        return RestResource.get(Routes.USERS, queryParams);
    }

    public static Response updateUser(User user, String id) {
        LoggerUtil.info("Updating user with ID: " + id);
        return RestResource.put(Routes.USER_BY_ID, user, id);
    }

    public static Response deleteUser(String id) {
        LoggerUtil.info("Deleting user with ID: " + id);
        return RestResource.delete(Routes.USER_BY_ID, id);
    }
}
