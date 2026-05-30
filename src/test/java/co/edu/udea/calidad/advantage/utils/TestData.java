package co.edu.udea.calidad.advantage.utils;

public class TestData {
    public static final String URL = "https://advantageonlineshopping.com/";
    public static final String REGISTER_URL = "https://advantageonlineshopping.com/#/register";

    public static final String PASSWORD = "Jose12345";

    public static final String VALID_USER = "mercury";
    public static final String VALID_PASSWORD = "Mercury123";

    public static final String INVALID_USER = "invalid_user_qa";
    public static final String INVALID_PASSWORD = "invalid_password_qa";

    public static String uniqueUser() {
        return "jv" + (System.currentTimeMillis() % 100000000L);
    }

    public static String email(String user) {
        return user + "@test.com";
    }
}
