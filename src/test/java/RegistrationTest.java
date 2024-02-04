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
import pageobject.RegisterPageObject;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
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
    }

    @After
    public void deleteUser() {
        accessToken = userPens.loginUser(user)
                .then()
                .extract().path("accessToken");
        if (accessToken != null) {
            userPens.deleteUser(accessToken);
        }
        driver.quit();
    }

    public void registration() {
        driver.get("https://stellarburgers.nomoreparties.site/register");


        RegisterPageObject registerPageObject = new RegisterPageObject(driver);
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        HomePageObject homePageObject = new HomePageObject(driver);
        registerPageObject.waitForLoad();
        registerPageObject.setName(nameUser);
        registerPageObject.setEmail(emailUser);
        registerPageObject.setPassword(passwordUser);
        registerPageObject.clickToRegisterButton();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(emailUser);
        loginPageObject.setPassword(passwordUser);
        loginPageObject.clickToSignInButton();
        homePageObject.waitForLoadMakeOrderButton();
        WebElement orderWindowElement = driver.findElement(By.xpath(".//button[text() = 'Оформить заказ']"));
        assertTrue("Ошибка", orderWindowElement.isDisplayed());
    }

    public void registrationNegative() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPageObject registerPageObject = new RegisterPageObject(driver);
        registerPageObject.waitForLoad();
        registerPageObject.setName(nameUser);
        registerPageObject.setEmail(emailUser);
        registerPageObject.setPassword("123qw");
        registerPageObject.clickToRegisterButton();
        registerPageObject.checkErrorMessage();
        WebElement orderWindowElement = driver.findElement(By.xpath(".//p[text() = 'Некорректный пароль']"));
        assertTrue("Ошибка", orderWindowElement.isDisplayed());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя. Chrome")
    public void registrationChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        registration();
    }

    @Test
    @DisplayName("Проверка регистрации пользователя. Yandex")
    public void registrationYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        registration();
    }
    @Test
    @DisplayName("Проверка ошибки при некорректном пароле. Chrome")
    public void negativeRegistrationChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        registrationNegative();
    }

    @Test
    @DisplayName("Проверка ошибки при некорректном пароле. Yandex")
    public void negativeRegistrationYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        registrationNegative();
    }
}
