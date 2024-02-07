import api.api.UserApi;
import api.url.BaseUrl;
import api.user.User;
import browser.Browser;
import constants.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
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
    static String uncorrectPassword = "123qw";
    User user = new User(emailUser, passwordUser, nameUser);
    User uncorrectUser = new User(emailUser, uncorrectPassword, nameUser);
    UserApi userApi = new UserApi();
    private WebDriver driver;
    private Browser browser = new Browser();

    @Before
    public void setUp() {
        BaseUrl.setUp();
        driver = browser.getWebDriver();
    }

    @After
    public void closeBrowser() {
        String accessToken = userApi.loginUser(user)
                .then()
                .extract().path("accessToken");
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
        String accessTokenUncorrectUser = userApi.loginUser(uncorrectUser)
                .then()
                .extract().path("accessToken");
        if (accessTokenUncorrectUser != null) {
            userApi.deleteUser(accessTokenUncorrectUser);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Проверка регистрации пользователя")
    public void registration() {
        driver.get(Constants.SITE_REGISTER);
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
        assertTrue("Ошибка", homePageObject.isOrderButtonDisplayed());

    }

    @Test
    @DisplayName("Проверка ошибки при некорректном пароле")
    public void registrationNegative() {
        driver.get(Constants.SITE_REGISTER);
        RegisterPageObject registerPageObject = new RegisterPageObject(driver);
        registerPageObject.waitForLoad();
        registerPageObject.setName(nameUser);
        registerPageObject.setEmail(emailUser);
        registerPageObject.setPassword(uncorrectPassword);
        registerPageObject.clickToRegisterButton();
        registerPageObject.checkErrorMessage();
        assertTrue("Ошибка", registerPageObject.isErrorMessageDisplayed());
    }

}
