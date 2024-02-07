import api.url.BaseUrl;
import browser.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.HomePageObject;

public class ConstructorTest {
    private WebDriver driver;
    private Browser browser = new Browser();

    @Before
    public void setUp() {
        driver = browser.getWebDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка выбора раздела Соусы")
    public void menuSauceIsActiveByClick() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.open();
        homePageObject.waitForLoadPage();
        homePageObject.clickToSauce();
        Assert.assertEquals("Соусы", homePageObject.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Проверка выбора раздела Булки")
    public void menuBunIsActiveByClick() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.open();
        homePageObject.waitForLoadPage();
        homePageObject.clickToSauce();
        homePageObject.clickToBuns();
        Assert.assertEquals("Булки", homePageObject.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Проверка выбора раздела Начинки")
    public void menuFillingIsActiveByClick() {
        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.open();
        homePageObject.waitForLoadPage();
        homePageObject.clickToFilling();
        Assert.assertEquals("Начинки", homePageObject.getTextFromSelectedMenu());
    }

}
