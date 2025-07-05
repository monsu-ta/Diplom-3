package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;

import static org.junit.Assert.assertTrue;

public class SuccessfulRegistrationTest extends BaseTest {
    @Before
    public void setUpTestUser() {
        createTestUser();
    }

    @Test
    @DisplayName("Тест успешной регистрации")
    @Description("Проверка регистрации нового пользователя")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillName(testUser.getName());
        registerPage.fillEmail(testUser.getEmail());
        registerPage.fillPassword(testUser.getPassword());
        registerPage.clickRegisterButton();

        assertTrue(loginPage.isLoginHeaderDisplayed());
    }
}