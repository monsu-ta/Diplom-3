package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import page.LoginPage;
import page.MainPage;
import page.RegisterPage;
import model.User;

import static org.junit.Assert.assertTrue;

public class LoginTests extends BaseTest {

    private User validUser;

    @Before
    public void setUpTestUser() {
        validUser = createAndRegisterTestUser();
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт»")
    @Description("Проверка входа через главную кнопку «Войти в аккаунт» на главной странице")
    public void testLoginFromMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickLoginInAccountButton();

        loginPage.enterEmail(validUser.getEmail());
        loginPage.enterPassword(validUser.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Должна отображаться кнопка оформления заказа",
                new MainPage(driver).isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через кнопку «Личный кабинет» в хедере")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickPersonalAccountButton();

        loginPage.enterEmail(validUser.getEmail());
        loginPage.enterPassword(validUser.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Должна отображаться кнопка оформления заказа",
                new MainPage(driver).isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме регистрации")
    @Description("Проверка входа через кнопку «Войти» на странице регистрации")
    public void testLoginFromRegistrationFormButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        registerPage.clickEnterButtonRegisterForm();

        loginPage.enterEmail(validUser.getEmail());
        loginPage.enterPassword(validUser.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Должна отображаться кнопка оформления заказа",
                new MainPage(driver).isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» в форме восстановления пароля")
    @Description("Проверка входа через кнопку «Войти» в форме восстановления пароля")
    public void testLoginFromPasswordRecoveryFormButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        registerPage.clickForgotPasswordButton();
        registerPage.clickEnterButtonForgotPasswordForm();

        loginPage.enterEmail(validUser.getEmail());
        loginPage.enterPassword(validUser.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Должна отображаться кнопка оформления заказа",
                new MainPage(driver).isOrderButtonDisplayed());
    }
}