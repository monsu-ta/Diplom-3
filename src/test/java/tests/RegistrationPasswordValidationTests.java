package tests;

import core.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;

import model.User;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class RegistrationPasswordValidationTests extends BaseTest {

    @Before
    public void setUpTestUser() {
        createTestUser();
    }

    @Parameterized.Parameter
    public String testName;

    @Parameterized.Parameter(1)
    public String password;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"1 символ", "1"},
                {"4 символа", "1234"},
                {"5 символов", "12345"}
        });
    }

    @Test
    @DisplayName("Проверка валидности пароля")
    @Description("Проверка ошибки при вводе некорректного пароля (менее 6 символов)")
    public void testPasswordValidation() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillName(testUser.getName());
        registerPage.fillEmail(testUser.getEmail());
        registerPage.fillPassword(password);
        registerPage.clickRegisterButton();

        assertTrue(registerPage.isPasswordErrorDisplayed());
    }
}