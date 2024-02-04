import api.pens.UserPens;
import api.url.BaseUrl;
import api.user.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;
import pageobject.RecoveryPasswordPageObject;
import pageobject.RegisterPageObject;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    static String emailUser = "username" + new Random().nextInt(10000) + "@yandex.ru";
    static String passwordUser = "userpas" + new Random().nextInt(10000);
    static String nameUser = "user" + new Random().nextInt(10000);
    User user = new User(emailUser, passwordUser, nameUser);
    UserPens userPens = new UserPens();
    private String accessToken;
    private WebDriver driver;

    @Before
    public void setUp() {
        BaseUrl.setUp();
        userPens.createUser(user);
        accessToken = userPens.loginUser(user)
                .then()
                .extract()
                .path("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userPens.deleteUser(accessToken);
        }
        driver.quit();
    }

    public void loginToSignInButton() {
        HomePageObject homePageObject = new HomePageObject(driver);
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        homePageObject.open();
        homePageObject.waitForLoadPage();
        homePageObject.clickToSignIn();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(emailUser);
        loginPageObject.setPassword(passwordUser);
        loginPageObject.clickToSignInButton();
        homePageObject.waitForLoadMakeOrderButton();
        WebElement orderWindowElement = driver.findElement(By.xpath(".//button[text() = 'Оформить заказ']"));
        assertTrue("Ошибка", orderWindowElement.isDisplayed());
    }

    public void loginThroughPersonalAccountButton() {
        HomePageObject homePageObject = new HomePageObject(driver);
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        homePageObject.open();
        homePageObject.waitForLoadPage();
        homePageObject.clickToPersonalAccountButton();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(emailUser);
        loginPageObject.setPassword(passwordUser);
        loginPageObject.clickToSignInButton();
        homePageObject.waitForLoadMakeOrderButton();
        WebElement orderWindowElement = driver.findElement(By.xpath(".//button[text() = 'Оформить заказ']"));
        assertTrue("Ошибка", orderWindowElement.isDisplayed());
    }

    public void loginThroughRegistredPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        HomePageObject homePageObject = new HomePageObject(driver);
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        RegisterPageObject registerPageObject = new RegisterPageObject(driver);
        registerPageObject.clickToSignIn();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(emailUser);
        loginPageObject.setPassword(passwordUser);
        loginPageObject.clickToSignInButton();
        homePageObject.waitForLoadMakeOrderButton();
        WebElement orderWindowElement = driver.findElement(By.xpath(".//button[text() = 'Оформить заказ']"));
        assertTrue("Ошибка", orderWindowElement.isDisplayed());
    }

    public void loginThroughRecoveryPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        HomePageObject homePageObject = new HomePageObject(driver);
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        RecoveryPasswordPageObject recoveryPasswordPageObject = new RecoveryPasswordPageObject(driver);
        recoveryPasswordPageObject.clickForSignInButton();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(emailUser);
        loginPageObject.setPassword(passwordUser);
        loginPageObject.clickToSignInButton();
        homePageObject.waitForLoadMakeOrderButton();
        WebElement orderWindowElement = driver.findElement(By.xpath(".//button[text() = 'Оформить заказ']"));
        assertTrue("Ошибка", orderWindowElement.isDisplayed());
    }


    @Test
    @DisplayName("Проверка входа по кнопке на главной странице. Chrome")
    public void loginToSignInButtonChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        loginToSignInButton();
    }

    @Test
    @DisplayName("Проверка входа по кнопке на главной странице. Yandex")
    public void loginToSignInButtonYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        loginToSignInButton();
    }

    @Test
    @DisplayName("Проверка входа через кнопку личный кабинет. Chrome")
    public void loginThroughPersonalAccountButtonChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        loginThroughPersonalAccountButton();
    }

    @Test
    @DisplayName("Проверка входа через кнопку личный кабинет. Yandex")
    public void loginThroughPersonalAccountButtonYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        loginThroughPersonalAccountButton();
    }

    @Test
    @DisplayName("Проверка входа черезкнопку в форме регистрации. Chrome")
    public void loginThroughRegistredPageChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        loginThroughRegistredPage();
    }

    @Test
    @DisplayName("Проверка входа черезкнопку в форме регистрации. Yandex")
    public void loginThroughRegistredPageYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        loginThroughRegistredPage();
    }

    @Test
    @DisplayName("Проверка входа через форму восстановления пароля. Chrome")
    public void loginThroughRecoveryPageChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        loginThroughRecoveryPage();
    }

    @Test
    @DisplayName("Проверка входа через форму восстановления пароля. Yandex")
    public void loginThroughRecoveryPageYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        loginThroughRecoveryPage();
    }
}
