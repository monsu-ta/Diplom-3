package tests;

import api.UserClient;
import core.WebDriverFactory;

import model.User;
import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected User testUser;
    protected final UserClient userClient = new UserClient();

    @Before
    public void setUp() {
        initializeDriver();
    }

    protected void initializeDriver() {
        driver = WebDriverFactory.createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected void createTestUser() {
        testUser = User.getRandomUser();

        String timestamp = String.valueOf(System.currentTimeMillis());
        testUser = new User(
                "test-" + timestamp + "@example.com",
                "password-" + timestamp,
                "user-" + timestamp
        );

        System.out.println("Created test user: " + testUser.getEmail());
    }

    protected User createAndRegisterTestUser() {
        User user = User.getRandomUser();
        userClient.createUser(user)
                .then()
                .statusCode(200);
        return user;
    }

    @After
    public void tearDown() {
        deleteTestUser();
        quitDriver();
    }

    protected void deleteTestUser() {
        if (testUser != null) {
            userClient.deleteUser(testUser);
        }
    }

    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}