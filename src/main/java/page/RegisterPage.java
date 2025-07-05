package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By nameField = By.xpath("//input[@name='name']");
    private final By emailField = By.xpath(".//fieldset[2]//input");
    private final By passwordField = By.xpath(".//input[@type='password']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    public final By passwordError = By.xpath(".//p[contains(text(),'Некорректный пароль')]");
    private final By forgotPasswordButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");
    private final By enterButtonRegisterForm = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    private final By enterButtonForgotPasswordForm = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввод имени")
    public void fillName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    @Step("Ввод email")
    public void fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void fillPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Клик кнопки «Зарегистрироваться»")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Проверка отображения ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }

    @Step("Получение текста ошибки пароля")
    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }

    @Step("Клик кнопки «Восстановить пароль»")
    public void clickForgotPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton)).click();
    }

    @Step("Клик кнопки «Войти» в форме регистрации")
    public void clickEnterButtonRegisterForm() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButtonRegisterForm)).click();
    }

    @Step("Клик кнопки «Войти» в форме восстановления пароля")
    public void clickEnterButtonForgotPasswordForm() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButtonForgotPasswordForm)).click();
    }

}