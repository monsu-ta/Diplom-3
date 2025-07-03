package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    private final By passwordError = By.xpath(".//p[contains(text(),'Некорректный пароль')]");
    private final By forgotPasswordButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");
    private final By enterButtonRegisterForm = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    private final By enterButtonForgotPasswordForm = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    public void fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void fillPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public boolean isPasswordErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }

    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }

    public void clickForgotPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton)).click();
    }

    public void clickEnterButtonRegisterForm() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButtonRegisterForm)).click();
    }

    public void clickEnterButtonForgotPasswordForm() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButtonForgotPasswordForm)).click();
    }

}