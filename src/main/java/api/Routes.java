package api;

import utils.ConfigReader;

public class Routes {
    public static final String BASE_URI = ConfigReader.getProperty("base.uri");
    public static final String BASE_PATH = ConfigReader.getProperty("base.path");
    
    // User Module Endpoints
    public static final String USERS = "/users";
    public static final String USER_BY_ID = "/users/{id}";
}
