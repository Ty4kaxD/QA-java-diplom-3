package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObject {

    private final WebDriver driver;

    //Поле Email
    private By emailField = By.xpath("//input[@name='name']");
    //Поле Пароль
    private By passwordField = By.xpath("//input[@name='Пароль']");
    //Кнопка Войти
    private final By signInButton = By.xpath(".//button[text() = 'Войти']");
    //Кнопка Зарегистироваться
    private By registerButton = By.xpath("//a[text()='Зарегистрироваться']");
    //Надпись Вход
    private By titleEnter = By.xpath("//h2[text()='Вход']");

    @Step("ввод Email в поле")
    public void setEmail(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
    }

    @Step("ввод пароля в поле")
    public void setPassword(String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    @Step("клик по кнопке Войти")
    public void clickToSignInButton() {
        driver.findElement(signInButton).click();
    }
    @Step("клик по кнопке Зарегистрироваться")
    public void clickToRegisterButton() {
        driver.findElement(registerButton).click();
    }
    @Step("Ожидание загрузки страницы")
    public void waitForLoad() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(titleEnter));
    }

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }
    @Step("проверка что отображается кнопка Вход")
    public boolean isSignInButtonDisplayed() {
        WebElement pageWindowElement = driver.findElement(titleEnter);
        return pageWindowElement.isDisplayed();
    }
}
