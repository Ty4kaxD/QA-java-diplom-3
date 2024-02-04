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

    public void clickLogoAndConstructorNavigation() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
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
        WebElement pageWindowElement = driver.findElement(By.xpath(".//button[text() = 'Оформить заказ']"));
        assertTrue("Ошибка", pageWindowElement.isDisplayed());

    }
    @Test
    @DisplayName("Проверка перехода на лого и на кнопку конструктор. Chrome")
    public void clickLogoAndConstructorNavigationChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        clickLogoAndConstructorNavigation();

    }

    @Test
    @DisplayName("Проверка перехода на лого и на кнопку конструктор. Yandex")
    public void clickLogoAndConstructorNavigationYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        clickLogoAndConstructorNavigation();
    }

}
