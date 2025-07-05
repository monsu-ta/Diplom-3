package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class WebDriverFactory {
    public enum BrowserType {
        CHROME,
        YANDEX
    }

    public static WebDriver createDriver() {
        BrowserType browserType = getBrowserTypeFromConfig();
        return createDriver(browserType);
    }

    public static WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                return createChromeDriver();
            case YANDEX:
                return createYandexDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    private static BrowserType getBrowserTypeFromConfig() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/config.properties"));
            return BrowserType.valueOf(props.getProperty("browser").toUpperCase());
        } catch (IOException e) {
            return BrowserType.CHROME; // default
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver createYandexDriver() {
        String driverPath = System.getenv("YANDEX_DRIVER_PATH");
        if (driverPath == null) {
            driverPath = "C:\\drivers\\yandexdriver.exe";
        }

        File yandexDriver = new File(driverPath);
        if (!yandexDriver.exists()) {
            throw new RuntimeException("YandexDriver not found at: " + yandexDriver.getAbsolutePath());
        }

        System.setProperty("webdriver.chrome.driver", yandexDriver.getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        options.setBinary(findYandexBrowserPath());
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }

    private static String findYandexBrowserPath() {
        Path[] possiblePaths = {
                Paths.get(System.getenv("LOCALAPPDATA"), "Yandex", "YandexBrowser", "Application", "browser.exe"),
                Paths.get(System.getenv("ProgramFiles"), "Yandex", "YandexBrowser", "browser.exe"),
                Paths.get(System.getenv("ProgramFiles(x86)"), "Yandex", "YandexBrowser", "browser.exe")
        };

        for (Path path : possiblePaths) {
            if (path.toFile().exists()) {
                return path.toString();
            }
        }
        throw new RuntimeException("Yandex browser not found");
    }
}