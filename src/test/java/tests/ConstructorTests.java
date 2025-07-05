package tests;

import core.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.ConstructorPage;
import page.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ConstructorTests extends BaseTest {
    @Parameterized.Parameter
    public WebDriverFactory.BrowserType browserType;

    @Parameterized.Parameters(name = "{0}")
    public static WebDriverFactory.BrowserType[] browserTypes() {
        return new WebDriverFactory.BrowserType[] {
                WebDriverFactory.BrowserType.CHROME,
                WebDriverFactory.BrowserType.YANDEX
        };
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка перехода к разделу «Булки» после перехода в раздел «Соусы»")

    public void testBunsSection() {

        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();
        constructorPage.clickBunsTab();

        assertEquals("Булки", constructorPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка перехода к разделу «Соусы»")
    public void testSaucesSection() {

        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();

        assertEquals("Соусы", constructorPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка перехода к разделу «Начинки»")
    public void testFillingsSection() {

        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickFillingsTab();

        assertEquals("Начинки", constructorPage.getActiveTabText());
    }
}