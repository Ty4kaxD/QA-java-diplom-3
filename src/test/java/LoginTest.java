import api.api.UserApi;
import api.url.BaseUrl;
import api.user.User;
import browser.Browser;
import constants.Constants;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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
    UserApi userApi = new UserApi();
    private String accessToken;
    private WebDriver driver;
    private Browser browser = new Browser();

    @Before
    public void setUp() {
        BaseUrl.setUp();
        userApi.createUser(user);
        accessToken = userApi.loginUser(user)
                .then()
                .extract()
                .path("accessToken");
        driver = browser.getWebDriver();
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Проверка входа по кнопке на главной странице")
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
        assertTrue("Ошибка", homePageObject.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа через кнопку личный кабинет")
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
        assertTrue("Ошибка", homePageObject.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа черезкнопку в форме регистрации")
    public void loginThroughRegistredPage() {
        driver.get(Constants.SITE_REGISTER);
        HomePageObject homePageObject = new HomePageObject(driver);
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        RegisterPageObject registerPageObject = new RegisterPageObject(driver);
        registerPageObject.clickToSignIn();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(emailUser);
        loginPageObject.setPassword(passwordUser);
        loginPageObject.clickToSignInButton();
        homePageObject.waitForLoadMakeOrderButton();
        assertTrue("Ошибка", homePageObject.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа через форму восстановления пароля")
    public void loginThroughRecoveryPage() {
        driver.get(Constants.SITE_FORGOT_PASSWORD);
        HomePageObject homePageObject = new HomePageObject(driver);
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        RecoveryPasswordPageObject recoveryPasswordPageObject = new RecoveryPasswordPageObject(driver);
        recoveryPasswordPageObject.clickForSignInButton();
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(emailUser);
        loginPageObject.setPassword(passwordUser);
        loginPageObject.clickToSignInButton();
        homePageObject.waitForLoadMakeOrderButton();
        assertTrue("Ошибка", homePageObject.isOrderButtonDisplayed());
    }

}
