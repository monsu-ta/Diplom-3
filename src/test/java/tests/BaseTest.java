package tests;

import api.UserClient;
import core.WebDriverFactory;

import io.qameta.allure.Step;
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

    @Step("Инициализация драйвера")
    protected void initializeDriver() {
        driver = WebDriverFactory.createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Step("Создание тестового пользователя")
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
    @Step("Создание и регистрация тестового пользователя")
    protected User createAndRegisterTestUser() {
        User user = User.getRandomUser();
        userClient.createUser(user)
                .then()
                .statusCode(200);
        return user;
    }

    @After
    @Step("Удаление тестового пользователя (при его наличии) и закрытие браузера")
    public void tearDown() {
        try {
            if (testUser != null) {
                userClient.deleteUser(testUser);
            }
        } catch (Exception e) {
            System.out.println("Проблема удаления пользователя: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

}