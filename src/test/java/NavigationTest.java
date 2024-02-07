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
import pageobject.AccountPageObject;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class NavigationTest {
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
    @DisplayName("Проверка перехода на лого и на кнопку конструктор")
    public void clickLogoAndConstructorNavigation() {
        driver.get(Constants.SITE_LOGIN);
        HomePageObject homePageObject = new HomePageObject(driver);
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        AccountPageObject accountPageObject = new AccountPageObject(driver);
        loginPageObject.waitForLoad();
        loginPageObject.setEmail(emailUser);
        loginPageObject.setPassword(passwordUser);
        loginPageObject.clickToSignInButton();
        homePageObject.waitForLoadMakeOrderButton();
        homePageObject.clickToPersonalAccountButton();
        accountPageObject.waitForLoad();
        accountPageObject.clickToConstructor();
        homePageObject.waitForLoadMakeOrderButton();
        homePageObject.clickToPersonalAccountButton();
        accountPageObject.waitForLoad();
        accountPageObject.clickToLogo();
        homePageObject.waitForLoadMakeOrderButton();
        assertTrue("Ошибка", homePageObject.isOrderButtonDisplayed());

    }

}
