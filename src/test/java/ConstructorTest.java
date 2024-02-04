import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.HomePageObject;

public class ConstructorTest {
    private WebDriver driver;

    @After
    public void tearDown() {
        driver.quit();
    }

    public void menuSauceIsActiveByClick() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.open();
        homePageObject.waitForLoadPage();
        homePageObject.clickToSauce();
        Assert.assertEquals("Соусы", homePageObject.getTextFromSelectedMenu());
    }

    public void menuBunIsActiveByClick() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.open();
        homePageObject.waitForLoadPage();
        homePageObject.clickToSauce();
        homePageObject.clickToBuns();
        Assert.assertEquals("Булки", homePageObject.getTextFromSelectedMenu());
    }

    public void menuFillingIsActiveByClick() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.open();
        homePageObject.waitForLoadPage();
        homePageObject.clickToFilling();
        Assert.assertEquals("Начинки", homePageObject.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Проверка выбора раздела Соусы. Chrome")
    public void menuSauceIsActiveByClickChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        menuSauceIsActiveByClick();
    }

    @Test
    @DisplayName("Проверка выбора раздела Булки. Chrome")
    public void menuBunIsActiveByClickChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        menuBunIsActiveByClick();
    }

    @Test
    @DisplayName("Проверка выбора раздела Начинки. Chrome")
    public void menuFillingIsActiveByClickChrome() {
        WebDriverManager.chromedriver().driverVersion("121").setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
        menuFillingIsActiveByClick();
    }

    @Test
    @DisplayName("Проверка выбора раздела Соусы. Yandex")
    public void menuSauceIsActiveByClickYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        menuSauceIsActiveByClick();
    }

    @Test
    @DisplayName("Проверка выбора раздела Булки. Yandex")
    public void menuBunIsActiveByClickYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        menuBunIsActiveByClick();
    }

    @Test
    @DisplayName("Проверка выбора раздела Начинки. Yandex")
    public void menuFillingIsActiveByClickChromeYandex() {
        WebDriverManager.chromedriver().driverVersion("96").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1800, 1000));
        menuFillingIsActiveByClickChrome();
    }
}
