package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPageObject {
    private final WebDriver driver;

    public AccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    //кнопка "Выход"
    private final By exitButton = By.xpath(".//button[text() = 'Выход']");

    //кнопка "Конструктор" в шапке страницы
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");

    //кнопка логотипа в шапке страницы
    private final By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");
    //Кнопка Профиль
    private By profileButton = By.xpath("//a[@href='/account/profile']");

    @Step("клик по кнопке выхода")
    public void clickToExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("клик по кнопке Конструктор")
    public void clickToConstructor() {
        driver.findElement(constructorButton).click();
    }

    @Step("клик по кнопке логотипу")
    public void clickToLogo() {
        driver.findElement(logoButton).click();
    }

    @Step("ожидание загрузки страницы")
    public void waitForLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }
}
